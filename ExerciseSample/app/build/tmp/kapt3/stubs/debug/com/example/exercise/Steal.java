package com.example.exercise;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004B\u0005\u00a2\u0006\u0002\u0010\u0005J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0010\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0013H\u0016J \u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020\u001aH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082D\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0007X\u0082D\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lcom/example/exercise/Steal;", "Lcom/google/android/gms/wearable/WearableListenerService;", "Lcom/google/android/gms/wearable/DataClient$OnDataChangedListener;", "Lcom/google/android/gms/wearable/MessageClient$OnMessageReceivedListener;", "Lcom/google/android/gms/wearable/CapabilityClient$OnCapabilityChangedListener;", "()V", "APP_OPEN_WEARABLE_PAYLOAD_PATH", "", "MESSAGE_ITEM_RECEIVED_PATH", "TAG_MESSAGE_RECEIVED", "activityContext", "Landroid/content/Context;", "messageEvent", "Lcom/google/android/gms/wearable/MessageEvent;", "mobileDeviceConnected", "", "mobileNodeUri", "wearableAppCheckPayloadReturnACK", "onCapabilityChanged", "", "p0", "Lcom/google/android/gms/wearable/CapabilityInfo;", "onDataChanged", "Lcom/google/android/gms/wearable/DataEventBuffer;", "onDestroy", "onStartCommand", "", "intent", "Landroid/content/Intent;", "flags", "startId", "app_debug"})
public final class Steal extends com.google.android.gms.wearable.WearableListenerService implements com.google.android.gms.wearable.DataClient.OnDataChangedListener, com.google.android.gms.wearable.MessageClient.OnMessageReceivedListener, com.google.android.gms.wearable.CapabilityClient.OnCapabilityChangedListener {
    private android.content.Context activityContext;
    private final java.lang.String TAG_MESSAGE_RECEIVED = "receive1";
    private final java.lang.String APP_OPEN_WEARABLE_PAYLOAD_PATH = "/APP_OPEN_WEARABLE_PAYLOAD";
    private boolean mobileDeviceConnected = false;
    private final java.lang.String wearableAppCheckPayloadReturnACK = "AppOpenWearableACK";
    private final java.lang.String MESSAGE_ITEM_RECEIVED_PATH = "/message-item-received";
    private com.google.android.gms.wearable.MessageEvent messageEvent;
    private java.lang.String mobileNodeUri;
    
    public Steal() {
        super();
    }
    
    @java.lang.Override()
    public int onStartCommand(@org.jetbrains.annotations.NotNull()
    android.content.Intent intent, int flags, int startId) {
        return 0;
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    @java.lang.Override()
    public void onDataChanged(@org.jetbrains.annotations.NotNull()
    com.google.android.gms.wearable.DataEventBuffer p0) {
    }
    
    @java.lang.Override()
    public void onCapabilityChanged(@org.jetbrains.annotations.NotNull()
    com.google.android.gms.wearable.CapabilityInfo p0) {
    }
}