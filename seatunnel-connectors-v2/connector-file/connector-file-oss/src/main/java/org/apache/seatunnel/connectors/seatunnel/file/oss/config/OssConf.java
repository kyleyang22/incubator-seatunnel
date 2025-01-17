/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.seatunnel.connectors.seatunnel.file.oss.config;

import org.apache.seatunnel.connectors.seatunnel.file.config.HadoopConf;

import org.apache.seatunnel.shade.com.typesafe.config.Config;

import org.apache.hadoop.fs.aliyun.oss.Constants;

import java.util.HashMap;

public class OssConf extends HadoopConf {
    private final String fsHdfsImpl = "org.apache.hadoop.fs.aliyun.oss.AliyunOSSFileSystem";

    @Override
    public String getFsHdfsImpl() {
        return fsHdfsImpl;
    }

    public OssConf(String hdfsNameKey) {
        super(hdfsNameKey);
    }

    public static HadoopConf buildWithConfig(Config config) {
        HadoopConf hadoopConf = new OssConf(config.getString(OssConfig.BUCKET));
        HashMap<String, String> ossOptions = new HashMap<>();
        ossOptions.put(Constants.ACCESS_KEY_ID, config.getString(OssConfig.ACCESS_KEY));
        ossOptions.put(Constants.ACCESS_KEY_SECRET, config.getString(OssConfig.ACCESS_SECRET));
        ossOptions.put(Constants.ENDPOINT_KEY, config.getString(OssConfig.ENDPOINT));
        hadoopConf.setExtraOptions(ossOptions);
        return hadoopConf;
    }
}
