package com.qait.test.open;

public final class FooException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FooException() {
		super();
	}

	public FooException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public FooException(final String message) {
		super(message);
	}

	public FooException(final Throwable cause) {
		super(cause);
	}
}
