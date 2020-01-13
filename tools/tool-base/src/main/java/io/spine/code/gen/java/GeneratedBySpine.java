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

package io.spine.code.gen.java;

/**
 * Provides fields for {@link javax.annotation.Generated Generated} annotation used by
 * code generation tools for marking source code generated by Spine Model Compiler.
 */
public final class GeneratedBySpine {

    private static final GeneratedBySpine INSTANCE = new GeneratedBySpine();

    /** Prevents instantiation from outside. */
    private GeneratedBySpine() {
    }

    public static GeneratedBySpine instance() {
        return INSTANCE;
    }

    /** Obtains the name for annotation field. */
    @SuppressWarnings("DuplicateStringLiteralInspection")
    // Each occurrence has a different semantics.
    public String fieldName() {
        return "value";
    }

    /** Obtains the code block for the annotation value. */
    public String codeBlock() {
        return "\"by Spine Model Compiler\"";
    }
}
