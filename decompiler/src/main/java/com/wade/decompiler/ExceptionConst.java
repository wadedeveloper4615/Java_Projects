package com.wade.decompiler;

public final class ExceptionConst {
    public enum EXCS {
        EXCS_CLASS_AND_INTERFACE_RESOLUTION, EXCS_FIELD_AND_METHOD_RESOLUTION, EXCS_INTERFACE_METHOD_RESOLUTION, EXCS_STRING_RESOLUTION, EXCS_ARRAY_EXCEPTION,
    }

    public static final Class<Throwable> THROWABLE = Throwable.class;
    public static final Class<RuntimeException> RUNTIME_EXCEPTION = RuntimeException.class;
    public static final Class<LinkageError> LINKING_EXCEPTION = LinkageError.class;
    public static final Class<ClassCircularityError> CLASS_CIRCULARITY_ERROR = ClassCircularityError.class;
    public static final Class<ClassFormatError> CLASS_FORMAT_ERROR = ClassFormatError.class;
    public static final Class<ExceptionInInitializerError> EXCEPTION_IN_INITIALIZER_ERROR = ExceptionInInitializerError.class;
    public static final Class<IncompatibleClassChangeError> INCOMPATIBLE_CLASS_CHANGE_ERROR = IncompatibleClassChangeError.class;
    public static final Class<AbstractMethodError> ABSTRACT_METHOD_ERROR = AbstractMethodError.class;
    public static final Class<IllegalAccessError> ILLEGAL_ACCESS_ERROR = IllegalAccessError.class;
    public static final Class<InstantiationError> INSTANTIATION_ERROR = InstantiationError.class;
    public static final Class<NoSuchFieldError> NO_SUCH_FIELD_ERROR = NoSuchFieldError.class;
    public static final Class<NoSuchMethodError> NO_SUCH_METHOD_ERROR = NoSuchMethodError.class;
    public static final Class<NoClassDefFoundError> NO_CLASS_DEF_FOUND_ERROR = NoClassDefFoundError.class;
    public static final Class<UnsatisfiedLinkError> UNSATISFIED_LINK_ERROR = UnsatisfiedLinkError.class;
    public static final Class<VerifyError> VERIFY_ERROR = VerifyError.class;
//    public static final Class UnsupportedClassVersionError = UnsupportedClassVersionError.class;
    public static final Class<NullPointerException> NULL_POINTER_EXCEPTION = NullPointerException.class;
    public static final Class<ArrayIndexOutOfBoundsException> ARRAY_INDEX_OUT_OF_BOUNDS_EXCEPTION = ArrayIndexOutOfBoundsException.class;
    public static final Class<ArithmeticException> ARITHMETIC_EXCEPTION = ArithmeticException.class;
    public static final Class<NegativeArraySizeException> NEGATIVE_ARRAY_SIZE_EXCEPTION = NegativeArraySizeException.class;
    public static final Class<ClassCastException> CLASS_CAST_EXCEPTION = ClassCastException.class;
    public static final Class<IllegalMonitorStateException> ILLEGAL_MONITOR_STATE = IllegalMonitorStateException.class;
    private static final Class<?>[] EXCS_CLASS_AND_INTERFACE_RESOLUTION = { NO_CLASS_DEF_FOUND_ERROR, CLASS_FORMAT_ERROR, VERIFY_ERROR, ABSTRACT_METHOD_ERROR, EXCEPTION_IN_INITIALIZER_ERROR, ILLEGAL_ACCESS_ERROR }; // Chapter 5.1
    private static final Class<?>[] EXCS_FIELD_AND_METHOD_RESOLUTION = { NO_SUCH_FIELD_ERROR, ILLEGAL_ACCESS_ERROR, NO_SUCH_METHOD_ERROR }; // Chapter 5.2
    private static final Class<?>[] EXCS_INTERFACE_METHOD_RESOLUTION = new Class[0]; // Chapter 5.3 (as below)
    private static final Class<?>[] EXCS_STRING_RESOLUTION = new Class[0];
    // Chapter 5.4 (no errors but the ones that _always_ could happen! How stupid.)
    private static final Class<?>[] EXCS_ARRAY_EXCEPTION = { NULL_POINTER_EXCEPTION, ARRAY_INDEX_OUT_OF_BOUNDS_EXCEPTION };

    public static Class<?>[] createExceptions(final EXCS type, final Class<?>... extraClasses) {
        switch (type) {
            case EXCS_CLASS_AND_INTERFACE_RESOLUTION:
                return mergeExceptions(EXCS_CLASS_AND_INTERFACE_RESOLUTION, extraClasses);
            case EXCS_ARRAY_EXCEPTION:
                return mergeExceptions(EXCS_ARRAY_EXCEPTION, extraClasses);
            case EXCS_FIELD_AND_METHOD_RESOLUTION:
                return mergeExceptions(EXCS_FIELD_AND_METHOD_RESOLUTION, extraClasses);
            case EXCS_INTERFACE_METHOD_RESOLUTION:
                return mergeExceptions(EXCS_INTERFACE_METHOD_RESOLUTION, extraClasses);
            case EXCS_STRING_RESOLUTION:
                return mergeExceptions(EXCS_STRING_RESOLUTION, extraClasses);
            default:
                throw new AssertionError("Cannot happen; unexpected enum value: " + type);
        }
    }

    // helper method to merge exception class arrays
    private static Class<?>[] mergeExceptions(final Class<?>[] input, final Class<?>... extraClasses) {
        final int extraLen = extraClasses == null ? 0 : extraClasses.length;
        final Class<?>[] excs = new Class<?>[input.length + extraLen];
        System.arraycopy(input, 0, excs, 0, input.length);
        if (extraLen > 0) {
            System.arraycopy(extraClasses, 0, excs, input.length, extraLen);
        }
        return excs;
    }
}
