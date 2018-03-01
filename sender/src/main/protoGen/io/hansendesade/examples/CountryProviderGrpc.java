package io.hansendesade.examples;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 * The greeter service definition.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.9.0)",
    comments = "Source: countryProvider.proto")
public final class CountryProviderGrpc {

  private CountryProviderGrpc() {}

  public static final String SERVICE_NAME = "CountryProvider";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getGetCountryMethod()} instead. 
  public static final io.grpc.MethodDescriptor<io.hansendesade.examples.CountryProviderOuterClass.CountryRequest,
      io.hansendesade.examples.CountryProviderOuterClass.CountryReply> METHOD_GET_COUNTRY = getGetCountryMethod();

  private static volatile io.grpc.MethodDescriptor<io.hansendesade.examples.CountryProviderOuterClass.CountryRequest,
      io.hansendesade.examples.CountryProviderOuterClass.CountryReply> getGetCountryMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<io.hansendesade.examples.CountryProviderOuterClass.CountryRequest,
      io.hansendesade.examples.CountryProviderOuterClass.CountryReply> getGetCountryMethod() {
    io.grpc.MethodDescriptor<io.hansendesade.examples.CountryProviderOuterClass.CountryRequest, io.hansendesade.examples.CountryProviderOuterClass.CountryReply> getGetCountryMethod;
    if ((getGetCountryMethod = CountryProviderGrpc.getGetCountryMethod) == null) {
      synchronized (CountryProviderGrpc.class) {
        if ((getGetCountryMethod = CountryProviderGrpc.getGetCountryMethod) == null) {
          CountryProviderGrpc.getGetCountryMethod = getGetCountryMethod = 
              io.grpc.MethodDescriptor.<io.hansendesade.examples.CountryProviderOuterClass.CountryRequest, io.hansendesade.examples.CountryProviderOuterClass.CountryReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "CountryProvider", "getCountry"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.hansendesade.examples.CountryProviderOuterClass.CountryRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.hansendesade.examples.CountryProviderOuterClass.CountryReply.getDefaultInstance()))
                  .setSchemaDescriptor(new CountryProviderMethodDescriptorSupplier("getCountry"))
                  .build();
          }
        }
     }
     return getGetCountryMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CountryProviderStub newStub(io.grpc.Channel channel) {
    return new CountryProviderStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CountryProviderBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new CountryProviderBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CountryProviderFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new CountryProviderFutureStub(channel);
  }

  /**
   * <pre>
   * The greeter service definition.
   * </pre>
   */
  public static abstract class CountryProviderImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public void getCountry(io.hansendesade.examples.CountryProviderOuterClass.CountryRequest request,
        io.grpc.stub.StreamObserver<io.hansendesade.examples.CountryProviderOuterClass.CountryReply> responseObserver) {
      asyncUnimplementedUnaryCall(getGetCountryMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetCountryMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                io.hansendesade.examples.CountryProviderOuterClass.CountryRequest,
                io.hansendesade.examples.CountryProviderOuterClass.CountryReply>(
                  this, METHODID_GET_COUNTRY)))
          .build();
    }
  }

  /**
   * <pre>
   * The greeter service definition.
   * </pre>
   */
  public static final class CountryProviderStub extends io.grpc.stub.AbstractStub<CountryProviderStub> {
    private CountryProviderStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CountryProviderStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CountryProviderStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CountryProviderStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public void getCountry(io.hansendesade.examples.CountryProviderOuterClass.CountryRequest request,
        io.grpc.stub.StreamObserver<io.hansendesade.examples.CountryProviderOuterClass.CountryReply> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getGetCountryMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * The greeter service definition.
   * </pre>
   */
  public static final class CountryProviderBlockingStub extends io.grpc.stub.AbstractStub<CountryProviderBlockingStub> {
    private CountryProviderBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CountryProviderBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CountryProviderBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CountryProviderBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public java.util.Iterator<io.hansendesade.examples.CountryProviderOuterClass.CountryReply> getCountry(
        io.hansendesade.examples.CountryProviderOuterClass.CountryRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getGetCountryMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * The greeter service definition.
   * </pre>
   */
  public static final class CountryProviderFutureStub extends io.grpc.stub.AbstractStub<CountryProviderFutureStub> {
    private CountryProviderFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CountryProviderFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CountryProviderFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CountryProviderFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_GET_COUNTRY = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final CountryProviderImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(CountryProviderImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_COUNTRY:
          serviceImpl.getCountry((io.hansendesade.examples.CountryProviderOuterClass.CountryRequest) request,
              (io.grpc.stub.StreamObserver<io.hansendesade.examples.CountryProviderOuterClass.CountryReply>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class CountryProviderBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    CountryProviderBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return io.hansendesade.examples.CountryProviderOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("CountryProvider");
    }
  }

  private static final class CountryProviderFileDescriptorSupplier
      extends CountryProviderBaseDescriptorSupplier {
    CountryProviderFileDescriptorSupplier() {}
  }

  private static final class CountryProviderMethodDescriptorSupplier
      extends CountryProviderBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    CountryProviderMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (CountryProviderGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CountryProviderFileDescriptorSupplier())
              .addMethod(getGetCountryMethod())
              .build();
        }
      }
    }
    return result;
  }
}
