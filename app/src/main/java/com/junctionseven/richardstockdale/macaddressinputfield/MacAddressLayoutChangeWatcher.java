package com.junctionseven.richardstockdale.macaddressinputfield;

/**
 * Created by richardstockdale on 13/03/2018.
 */

public interface MacAddressLayoutChangeWatcher {
    public void editingCompleted(MacAddressLayout macAddressLayout);

    public void deleteTapped(MacAddressLayout macAddressLayout);
}
