/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License
 * 2.0; you may not use this file except in compliance with the Elastic License
 * 2.0.
 */

package org.elasticsearch.xpack.inference.services.amazonbedrock.response;

import org.elasticsearch.inference.InferenceServiceResults;
import org.elasticsearch.xpack.inference.services.amazonbedrock.request.AmazonBedrockRequest;

public abstract class AmazonBedrockResponse {
    public abstract InferenceServiceResults accept(AmazonBedrockRequest request);
}
