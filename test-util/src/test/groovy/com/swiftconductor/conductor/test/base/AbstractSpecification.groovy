/*
 * Copyright 2021 Netflix, Inc.
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
package com.swiftconductor.test.base

import com.swiftconductor.service.WorkflowService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource

import com.swiftconductor.core.execution.AsyncSystemTaskExecutor
import com.swiftconductor.core.execution.WorkflowExecutor
import com.swiftconductor.core.reconciliation.WorkflowSweeper
import com.swiftconductor.service.ExecutionService
import com.swiftconductor.service.MetadataService
import com.swiftconductor.test.util.WorkflowTestUtil

import spock.lang.Specification

@SpringBootTest
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
abstract class AbstractSpecification extends Specification {

    @Autowired
    ExecutionService workflowExecutionService

    @Autowired
    MetadataService metadataService

    @Autowired
    WorkflowExecutor workflowExecutor

    @Autowired
    WorkflowService workflowService

    @Autowired
    WorkflowTestUtil workflowTestUtil

    @Autowired
    WorkflowSweeper workflowSweeper

    @Autowired
    AsyncSystemTaskExecutor asyncSystemTaskExecutor

    def cleanup() {
        workflowTestUtil.clearWorkflows()
    }

    void sweep(String workflowId) {
        workflowSweeper.sweep(workflowId)
    }
}
