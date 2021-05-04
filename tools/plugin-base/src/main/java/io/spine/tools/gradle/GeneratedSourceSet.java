/*
 * Copyright 2021, TeamDev. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
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

package io.spine.tools.gradle;

import com.google.common.annotations.VisibleForTesting;
import io.spine.code.AbstractDirectory;

import java.nio.file.Path;

import static io.spine.tools.fs.DirectoryName.grpc;
import static io.spine.tools.fs.DirectoryName.java;
import static io.spine.tools.fs.DirectoryName.resources;
import static io.spine.tools.fs.DirectoryName.spine;

/**
 * The generated code directory which belongs to a certain source set.
 *
 * <p>Each generated source set contains following items:
 * <ul>
 *     <li>{@code java}, {@code spine}, and {@code grpc} directories with Java code;
 *     <li>{@code resources} directory with resource files.
 * </ul>
 */
public final class GeneratedSourceSet extends AbstractDirectory {

    @VisibleForTesting
    static final String JAVA = java.value();

    @VisibleForTesting
    static final String SPINE = spine.value();

    @VisibleForTesting
    static final String GRPC = grpc.value();

    @VisibleForTesting
    static final String RESOURCES = resources.value();

    GeneratedSourceSet(Path path) {
        super(path);
    }

    /**
     * Obtains the root directory for Java sources generated by the Protobuf compiler.
     */
    public Path java() {
        return path().resolve(JAVA);
    }

    /**
     * Obtains the root directory for Java sources generated by the Model Compiler plugin.
     */
    public Path spine() {
        return path().resolve(SPINE);
    }

    /**
     * Obtains the root directory for Java sources generated by the gRPC Protobuf compiler.
     */
    public Path grpc() {
        return path().resolve(GRPC);
    }

    /**
     * Obtains the root directory for resources generated by the Model Compiler plugin.
     */
    public Path resources() {
        return path().resolve(RESOURCES);
    }
}
