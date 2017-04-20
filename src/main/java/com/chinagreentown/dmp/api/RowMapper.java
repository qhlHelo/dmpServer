package com.chinagreentown.dmp.api;

import org.apache.hadoop.hbase.client.Result;

/**
 * Implementations of this interface perform the actual work of mapping each row to a result object, but don't need to worry about exception handling.
 *
 * @desc copy from spring data hadoop hbase
 */

public interface RowMapper<T> {

    T mapRow(Result result, int rowNum) throws Exception;
}
