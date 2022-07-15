/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TSSLTransportFactory;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TSSLTransportFactory.TSSLTransportParameters;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.apache.thrift.transport.layered.TFramedTransport;

// Generated code
import tutorial.*;
import shared.*;

public class JavaServer2 {

  private static final int DEFAULT_PORT = 9092;
  private static final int DEFAULT_WORKERS = 5;
  private static final int DEFAULT_MAX_READ_BUFFER_BYTES = 16384000;
  private TServer server;
  // private int port;
  // private int workers;
  // private int maxReadBufferBytes;

  public static CalculatorHandler handler;

  public static Calculator.Processor processor;

  public static void main(String[] origin_args) {

    try {
      handler = new CalculatorHandler();
      processor = new Calculator.Processor(handler);

      TNonblockingServerTransport transport = new TNonblockingServerSocket(DEFAULT_PORT);
      THsHaServer.Args args = new THsHaServer.Args(transport);
      args.minWorkerThreads(DEFAULT_WORKERS);
      args.maxWorkerThreads(DEFAULT_WORKERS);
      args.processor(processor);
      args.transportFactory(new TFramedTransport.Factory(DEFAULT_MAX_READ_BUFFER_BYTES));
      args.protocolFactory(new TBinaryProtocol.Factory(false, false));
      args.maxReadBufferBytes = DEFAULT_MAX_READ_BUFFER_BYTES;
      TServer server = new THsHaServer(args);

      server.serve();
    } catch (Exception x) {
      x.printStackTrace();
    }
  }

}
