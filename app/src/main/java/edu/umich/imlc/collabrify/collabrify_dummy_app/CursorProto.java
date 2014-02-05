// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Cursor.proto

package edu.umich.imlc.collabrify.collabrify_dummy_app;

public final class CursorProto {
  private CursorProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface CursorActionOrBuilder
      extends com.google.protobuf.MessageOrBuilder {

    // optional int32 index = 3;
    /**
     * <code>optional int32 index = 3;</code>
     */
    boolean hasIndex();
    /**
     * <code>optional int32 index = 3;</code>
     */
    int getIndex();
  }
  /**
   * Protobuf type {@code edu.umich.imlc.collabrify.collabrify_dummy_app.CursorAction}
   */
  public static final class CursorAction extends
      com.google.protobuf.GeneratedMessage
      implements CursorActionOrBuilder {
    // Use CursorAction.newBuilder() to construct.
    private CursorAction(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private CursorAction(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final CursorAction defaultInstance;
    public static CursorAction getDefaultInstance() {
      return defaultInstance;
    }

    public CursorAction getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private CursorAction(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      initFields();
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 24: {
              bitField0_ |= 0x00000001;
              index_ = input.readInt32();
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e.getMessage()).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return edu.umich.imlc.collabrify.collabrify_dummy_app.CursorProto.internal_static_edu_umich_imlc_collabrify_collabrify_dummy_app_CursorAction_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return edu.umich.imlc.collabrify.collabrify_dummy_app.CursorProto.internal_static_edu_umich_imlc_collabrify_collabrify_dummy_app_CursorAction_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              edu.umich.imlc.collabrify.collabrify_dummy_app.CursorProto.CursorAction.class, edu.umich.imlc.collabrify.collabrify_dummy_app.CursorProto.CursorAction.Builder.class);
    }

    public static com.google.protobuf.Parser<CursorAction> PARSER =
        new com.google.protobuf.AbstractParser<CursorAction>() {
      public CursorAction parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new CursorAction(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<CursorAction> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    // optional int32 index = 3;
    public static final int INDEX_FIELD_NUMBER = 3;
    private int index_;
    /**
     * <code>optional int32 index = 3;</code>
     */
    public boolean hasIndex() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>optional int32 index = 3;</code>
     */
    public int getIndex() {
      return index_;
    }

    private void initFields() {
      index_ = 0;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;

      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeInt32(3, index_);
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(3, index_);
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static edu.umich.imlc.collabrify.collabrify_dummy_app.CursorProto.CursorAction parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static edu.umich.imlc.collabrify.collabrify_dummy_app.CursorProto.CursorAction parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static edu.umich.imlc.collabrify.collabrify_dummy_app.CursorProto.CursorAction parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static edu.umich.imlc.collabrify.collabrify_dummy_app.CursorProto.CursorAction parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static edu.umich.imlc.collabrify.collabrify_dummy_app.CursorProto.CursorAction parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static edu.umich.imlc.collabrify.collabrify_dummy_app.CursorProto.CursorAction parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static edu.umich.imlc.collabrify.collabrify_dummy_app.CursorProto.CursorAction parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static edu.umich.imlc.collabrify.collabrify_dummy_app.CursorProto.CursorAction parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static edu.umich.imlc.collabrify.collabrify_dummy_app.CursorProto.CursorAction parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static edu.umich.imlc.collabrify.collabrify_dummy_app.CursorProto.CursorAction parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(edu.umich.imlc.collabrify.collabrify_dummy_app.CursorProto.CursorAction prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code edu.umich.imlc.collabrify.collabrify_dummy_app.CursorAction}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements edu.umich.imlc.collabrify.collabrify_dummy_app.CursorProto.CursorActionOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return edu.umich.imlc.collabrify.collabrify_dummy_app.CursorProto.internal_static_edu_umich_imlc_collabrify_collabrify_dummy_app_CursorAction_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return edu.umich.imlc.collabrify.collabrify_dummy_app.CursorProto.internal_static_edu_umich_imlc_collabrify_collabrify_dummy_app_CursorAction_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                edu.umich.imlc.collabrify.collabrify_dummy_app.CursorProto.CursorAction.class, edu.umich.imlc.collabrify.collabrify_dummy_app.CursorProto.CursorAction.Builder.class);
      }

      // Construct using edu.umich.imlc.collabrify.collabrify_dummy_app.CursorProto.CursorAction.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        index_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return edu.umich.imlc.collabrify.collabrify_dummy_app.CursorProto.internal_static_edu_umich_imlc_collabrify_collabrify_dummy_app_CursorAction_descriptor;
      }

      public edu.umich.imlc.collabrify.collabrify_dummy_app.CursorProto.CursorAction getDefaultInstanceForType() {
        return edu.umich.imlc.collabrify.collabrify_dummy_app.CursorProto.CursorAction.getDefaultInstance();
      }

      public edu.umich.imlc.collabrify.collabrify_dummy_app.CursorProto.CursorAction build() {
        edu.umich.imlc.collabrify.collabrify_dummy_app.CursorProto.CursorAction result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public edu.umich.imlc.collabrify.collabrify_dummy_app.CursorProto.CursorAction buildPartial() {
        edu.umich.imlc.collabrify.collabrify_dummy_app.CursorProto.CursorAction result = new edu.umich.imlc.collabrify.collabrify_dummy_app.CursorProto.CursorAction(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.index_ = index_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof edu.umich.imlc.collabrify.collabrify_dummy_app.CursorProto.CursorAction) {
          return mergeFrom((edu.umich.imlc.collabrify.collabrify_dummy_app.CursorProto.CursorAction)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(edu.umich.imlc.collabrify.collabrify_dummy_app.CursorProto.CursorAction other) {
        if (other == edu.umich.imlc.collabrify.collabrify_dummy_app.CursorProto.CursorAction.getDefaultInstance()) return this;
        if (other.hasIndex()) {
          setIndex(other.getIndex());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        edu.umich.imlc.collabrify.collabrify_dummy_app.CursorProto.CursorAction parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (edu.umich.imlc.collabrify.collabrify_dummy_app.CursorProto.CursorAction) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      // optional int32 index = 3;
      private int index_ ;
      /**
       * <code>optional int32 index = 3;</code>
       */
      public boolean hasIndex() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>optional int32 index = 3;</code>
       */
      public int getIndex() {
        return index_;
      }
      /**
       * <code>optional int32 index = 3;</code>
       */
      public Builder setIndex(int value) {
        bitField0_ |= 0x00000001;
        index_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 index = 3;</code>
       */
      public Builder clearIndex() {
        bitField0_ = (bitField0_ & ~0x00000001);
        index_ = 0;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:edu.umich.imlc.collabrify.collabrify_dummy_app.CursorAction)
    }

    static {
      defaultInstance = new CursorAction(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:edu.umich.imlc.collabrify.collabrify_dummy_app.CursorAction)
  }

  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_edu_umich_imlc_collabrify_collabrify_dummy_app_CursorAction_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_edu_umich_imlc_collabrify_collabrify_dummy_app_CursorAction_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\014Cursor.proto\022.edu.umich.imlc.collabrif" +
      "y.collabrify_dummy_app\"\035\n\014CursorAction\022\r" +
      "\n\005index\030\003 \001(\005B=\n.edu.umich.imlc.collabri" +
      "fy.collabrify_dummy_appB\013CursorProto"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_edu_umich_imlc_collabrify_collabrify_dummy_app_CursorAction_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_edu_umich_imlc_collabrify_collabrify_dummy_app_CursorAction_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_edu_umich_imlc_collabrify_collabrify_dummy_app_CursorAction_descriptor,
              new java.lang.String[] { "Index", });
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
  }

  // @@protoc_insertion_point(outer_class_scope)
}
