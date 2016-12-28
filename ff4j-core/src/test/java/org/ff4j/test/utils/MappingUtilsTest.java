package org.ff4j.test.utils;

import java.util.ArrayList;

/*
 * #%L
 * ff4j-core
 * %%
 * Copyright (C) 2013 - 2016 FF4J
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.ff4j.cache.CacheManagerInMemory;
import org.ff4j.cache.FeatureStoreCacheProxy;
import org.ff4j.exception.InvalidStrategyTypeException;
import org.ff4j.feature.Feature;
import org.ff4j.feature.FlippingStrategy;
import org.ff4j.inmemory.FeatureStoreInMemory;
import org.ff4j.property.Property;
import org.ff4j.property.PropertyString;
import org.ff4j.utils.JdbcUtils;
import org.ff4j.utils.JsonUtils;
import org.ff4j.utils.Util;
import org.junit.Assert;
import org.junit.Test;


public class MappingUtilsTest {
 
    
    @Test
    public void testJsonMapping() {
        JsonUtils.permissionsAsJson(null);
        JsonUtils.customPropertiesAsJson(null);
        JsonUtils.customPropertiesAsJson( new HashMap<String, Property<?>>());
        JsonUtils.cacheJson(new FeatureStoreCacheProxy(new FeatureStoreInMemory(), new CacheManagerInMemory<Feature>()));
    }
    
    @Test
    public void testUtil() {
        Util.assertParamHasLength("toto", "tata");
        Set < String> ss = Util.setOf("one", "two");
        Assert.assertNotNull(ss);
        Util.assertTrue(true);
        Util.requireNull(null);
    }
    
    @Test(expected = InvalidStrategyTypeException.class)
    public void testIntanciateInvalidFlippingStrategy() {
       FlippingStrategy.instanciate("f1", "com.class.invalid", new HashMap<String, String>());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testAssert1() {
        Util.assertTrue(false);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testAssert3() {
        Util.assertParamHasLength("", "tata");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testAssert4() {
        Util.assertParamHasLength(null, "tata");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testAssert2() {
        Util.requireNull("");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testAssert6() {
        Util.assertNotEmpty(new ArrayList<String>());
    }
    
    @Test
    public void testRollbackingWithNull() {
        JdbcUtils.rollback(null);
    }
    
    @Test
    public void testJsonCustomProperties() {
        // Given
        Map < String , Property<?> > props = new HashMap<String, Property<?>>();
        props.put("f1", new PropertyString("f1","v1"));
        props.put("f2", new PropertyString("f2","v2"));
        // When
        String expression = JsonUtils.customPropertiesAsJson(props);
        // Then
        Assert.assertNotNull(expression);
    }

}
