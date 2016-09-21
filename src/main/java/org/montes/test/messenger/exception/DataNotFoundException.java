package org.montes.test.messenger.exception;

public class DataNotFoundException extends RuntimeException {

	/**
	 * https://www.youtube.com/watch?v=9oeJc_VkZxo&list=PLqq-6Pq4lTTZh5U8RbdXq0WaYvZBz2rbn&index=27
	 */
	private static final long serialVersionUID = 1L;
	
	public DataNotFoundException(String message) {
		super(message);
	}

}
