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

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.apache.thrift.transport.layered.TFramedTransport;

// Generated code
import tutorial.*;
import shared.*;

public class JavaServer2 {
  private static final int port = 9092;
  public static CalculatorHandler handler;
  public static Calculator.Processor processor;

  public static void main(String[] args) {

    try {
      handler = new CalculatorHandler();
      processor = new Calculator.Processor(handler);

      TNonblockingServerTransport transport = new TNonblockingServerSocket(port);

      THsHaServer.Args servArgs = new THsHaServer.Args(transport);
      servArgs.processor(processor);
      servArgs.transportFactory(new TFramedTransport.Factory());
      servArgs.protocolFactory(new TBinaryProtocol.Factory());

      TServer server = new THsHaServer(servArgs);
      System.out.println("Running THsHaServer Server");

      server.serve();
    } catch (Exception x) {
      x.printStackTrace();
    }
  }

}
