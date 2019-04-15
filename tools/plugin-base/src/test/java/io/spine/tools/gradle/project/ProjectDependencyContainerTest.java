/*
 * Copyright 2019, TeamDev. All rights reserved.
 *
 * Redistribution and use in source and/or binary forms, with or without
 * modification, must retain the above copyright notice and the following
 * disclaimer.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package io.spine.tools.gradle.project;

import io.spine.tools.gradle.Artifact;
import io.spine.tools.gradle.ConfigurationName;
import io.spine.tools.gradle.Dependency;
import io.spine.tools.gradle.ThirdPartyDependency;
import org.gradle.api.Project;
import org.gradle.api.artifacts.DependencySet;
import org.gradle.api.artifacts.ExcludeRule;
import org.gradle.api.internal.artifacts.DefaultExcludeRule;
import org.gradle.api.plugins.JavaPlugin;
import org.gradle.testfixtures.ProjectBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junitpioneer.jupiter.TempDirectory;
import org.junitpioneer.jupiter.TempDirectory.TempDir;

import java.nio.file.Path;
import java.util.Set;

import static com.google.common.collect.Iterables.getOnlyElement;
import static com.google.common.truth.Truth.assertThat;
import static io.spine.tools.gradle.ConfigurationName.IMPLEMENTATION;
import static io.spine.tools.gradle.ConfigurationName.RUNTIME_CLASSPATH;
import static io.spine.tools.gradle.ConfigurationName.TEST_RUNTIME_CLASSPATH;

@ExtendWith(TempDirectory.class)
@DisplayName("ProjectDependencyContainer should")
class ProjectDependencyContainerTest {

    private Project project;

    @BeforeEach
    void setUp(@TempDir Path projectPath) {
        project = ProjectBuilder
                .builder()
                .withProjectDir(projectPath.toFile())
                .build();
        project.getPluginManager()
               .apply(JavaPlugin.class);
    }

    @Test
    @DisplayName("add a given dependency")
    void addDependency() {
        ProjectDependencyContainer container = ProjectDependencyContainer.from(project);

        Artifact dependency = Artifact
                .newBuilder()
                .useSpineToolsGroup()
                .setName("test-artifact")
                .setVersion("42.0")
                .build();
        String notation = dependency.notation();
        container.depend(IMPLEMENTATION, notation);

        DependencySet dependencies = project.getConfigurations()
                                            .getByName(IMPLEMENTATION.value())
                                            .getDependencies();
        assertThat(dependencies).hasSize(1);
        Artifact actualDependency = Artifact.from(getOnlyElement(dependencies));
        assertThat(actualDependency).isEqualTo(dependency);
    }

    @Test
    @DisplayName("exclude dependencies")
    void excludeDependencies() {
        ProjectDependencyContainer container = ProjectDependencyContainer.from(project);
        Dependency unwanted = new ThirdPartyDependency("org.example.system", "system-core");
        container.exclude(unwanted);

        checkExcluded(RUNTIME_CLASSPATH, unwanted);
        checkExcluded(TEST_RUNTIME_CLASSPATH, unwanted);
    }

    private void checkExcluded(ConfigurationName fromConfiguration, Dependency unwanted) {
        Set<ExcludeRule> runtimeExclusionRules = project.getConfigurations()
                                                        .getByName(fromConfiguration.value())
                                                        .getExcludeRules();
        ExcludeRule excludeRule = new DefaultExcludeRule(unwanted.groupId(), unwanted.name());
        assertThat(runtimeExclusionRules).containsExactly(excludeRule);
    }
}
