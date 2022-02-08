package com.sunshine.freeform.systemapi;

import android.os.IInterface;

public final class WindowManager {
    private final IInterface manager;

    public WindowManager(IInterface manager) {
        this.manager = manager;
    }

    public int getRotation() {
        try {
            Class<?> cls = manager.getClass();
            try {
                return (Integer) manager.getClass().getMethod("getRotation").invoke(manager);
            } catch (NoSuchMethodException e) {
                // method changed since this commit:
                // https://android.googlesource.com/platform/frameworks/base/+/8ee7285128c3843401d4c4d0412cd66e86ba49e3%5E%21/#F2
                return (Integer) cls.getMethod("getDefaultDisplayRotation").invoke(manager);
            }
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }
}
