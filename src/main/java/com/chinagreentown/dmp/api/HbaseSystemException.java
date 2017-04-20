package com.chinagreentown.dmp.api;


/**
 * HBase Data Access exception.
 */

import org.springframework.dao.UncategorizedDataAccessException;

/**
 * @desc copy from spring data hadoop hbase
 */
public class HbaseSystemException extends UncategorizedDataAccessException {

    public HbaseSystemException(Exception cause) {
        super(cause.getMessage(), cause);
    }

    public HbaseSystemException(Throwable throwable) {
        super(throwable.getMessage(), throwable);
    }
}
