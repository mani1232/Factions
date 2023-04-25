package com.massivecraft.factions.config.file;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ConfigTest {
    //  Broken tests
    //@Test
    //public void testMainConfig() {
    //    testConfig(new MainConfig());
    //}
//
    //@Test
    //public void testPerms() {
    //    testConfig(new PermissionsConfig());
    //}

    private void testConfig(Object o) {
        try {
            assertNotNull(o, o.getClass());
        } catch (IllegalAccessException e) {
            throw new AssertionError("Couldn't even check the class!");
        }
    }

    private void assertNotNull(Object o, Class<?> clazz) throws IllegalAccessException {
        System.out.println(clazz);
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isSynthetic() || Modifier.isTransient(field.getModifiers())) {
                continue;
            }
            assertNotNull(o, field);
        }
    }

    private void assertNotNull(Object o, Field field) throws IllegalAccessException {
        field.setAccessible(true);
        Object oo = field.get(o);
        Assertions.assertNotNull(oo, field.getName() + " was null");
        if (oo.getClass().getPackage().getName().startsWith("com.massivecraft.factions.config")) {
            assertNotNull(oo, oo.getClass());
        }
    }
}
