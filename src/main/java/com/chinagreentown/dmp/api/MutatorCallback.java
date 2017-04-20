package com.chinagreentown.dmp.api;

import org.apache.hadoop.hbase.client.BufferedMutator;

/**
 * @desc callback for hbase put delete and update
 */
public interface MutatorCallback {

    /**
     * 使用mutator api to update put and delete
     *
     * @param mutator
     * @throws Throwable
     */
    void doInMutator(BufferedMutator mutator) throws Throwable;
}
