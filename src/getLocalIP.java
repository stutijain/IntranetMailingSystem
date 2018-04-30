// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   getLocalIP.java

import java.net.InetAddress;

public class getLocalIP
{

    public getLocalIP()
    {
    }

    public String HostName()
        throws Exception
    {
        InetAddress inetaddress = InetAddress.getLocalHost();
        String s = inetaddress.getHostName();
        return s;
    }
}
