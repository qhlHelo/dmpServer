package com.chinagreentown.dmp.api;


/**
 * HBase Data Access exception.
 *
 * @author Costin Leau
 */

import org.springframework.dao.UncategorizedDataAccessException;

/**
 * JThink@JThink
 *
 * @author JThink
 * @version 0.0.1
 * @desc copy from spring data hadoop hbase, modified by JThink
 * @date 2016-11-15 16:08:41
 */
public class HbaseSystemException extends UncategorizedDataAccessException {

    public HbaseSystemException(Exception cause) {
        super(cause.getMessage(), cause);
    }

    public HbaseSystemException(Throwable throwable) {
        super(throwable.getMessage(), throwable);
    }
}
