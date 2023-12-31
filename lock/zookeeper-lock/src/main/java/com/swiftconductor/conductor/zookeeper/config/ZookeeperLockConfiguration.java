/*
 * Copyright 2023 Swift Conductor Community Contributors.
 * (Code and content before December 13, 2023, Copyright Netflix Conductor Community Contributors.)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.swiftconductor.conductor.zookeeper.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.swiftconductor.conductor.core.sync.Lock;
import com.swiftconductor.conductor.zookeeper.lock.ZookeeperLock;

@Configuration
@EnableConfigurationProperties(ZookeeperProperties.class)
@ConditionalOnProperty(name = "conductor.workflow-execution-lock.type", havingValue = "zookeeper")
public class ZookeeperLockConfiguration {

    @Bean
    public Lock provideLock(ZookeeperProperties properties) {
        return new ZookeeperLock(properties);
    }
}
