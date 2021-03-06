package com.webonise.tv.device;

import com.webonise.tv.remote.TVRemoteClient;

/**
 * Created by mukuls-webonise on 9/8/16.
 */

//Abstraction for tv devices
public interface Television extends TVRemoteClient {

    void turnOn();

    void turnOff();

    boolean isOn();

    int setChannel(int channelNo);

    void nextChannel();

    void previousChannel();

    void upVolume();

    void downVolume();

    void upVolumeBy(int amount);

    void downVolumeBy(int amount);

    int getTotalChannels();

    int getCurrentChannel();

    int getCurrentVolume();

    int getMaxVolume();

}
