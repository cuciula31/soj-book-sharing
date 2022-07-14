package com.soj.booksharing.encoder;

import org.bouncycastle.crypto.generators.Argon2BytesGenerator;
import org.bouncycastle.crypto.params.Argon2Parameters;
import org.bouncycastle.crypto.*;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;

public class PasswordEncoder implements org.springframework.security.crypto.password.PasswordEncoder {

    private static final int DEFAULT_SALT_LENGTH = 16;
    private static final int DEFAULT_HASH_LENGTH = 32;
    private static final int DEFAULT_PARALLELISM = 1;
    private static final int DEFAULT_MEMORY = 1 << 12;
    private static final int DEFAULT_ITERATIONS = 3;

    private final int hashLength;
    private final int parallelism;
    private final int memory;
    private final int iterations;
    private final BytesKeyGenerator saltGenerator;

    public PasswordEncoder(int saltLength, int hashLength,
                                 int parallelism, int memory, int iterations) {

        this.hashLength = hashLength;
        this.parallelism = parallelism;
        this.memory = memory;
        this.iterations = iterations;

        this.saltGenerator = KeyGenerators.secureRandom(saltLength);
    }

    public PasswordEncoder() {
        this(DEFAULT_SALT_LENGTH, DEFAULT_HASH_LENGTH,
                DEFAULT_PARALLELISM, DEFAULT_MEMORY, DEFAULT_ITERATIONS);
    }

    @Override
    public String encode(CharSequence charSequence) {
        byte[] salt = saltGenerator.generateKey();
        byte[] hash = new byte[hashLength];

        Argon2Parameters params = new Argon2Parameters.Builder(Argon2Parameters.ARGON2_id).
                withSalt(salt).
                withParallelism(parallelism).
                withMemoryAsKB(memory).
                withIterations(iterations).
                build();
        Argon2BytesGenerator generator = new Argon2BytesGenerator();
        generator.init(params);
        generator.generateBytes(charSequence.toString().toCharArray(), hash);

        return null;
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return false;
    }
}
