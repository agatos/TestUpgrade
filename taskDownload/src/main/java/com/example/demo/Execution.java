package com.example.demo;

public class Execution {

		public static void main(String[] args) {
	        Thread new1 = new Thread(new DownloadTask("https://iweb.dl.sourceforge.net/project/reactos/ReactOS/0.4.14/ReactOS-0.4.14-iso.zip","/Users/a839240/Desktop/Upgrade/ReactOS-0.4.14-iso.zip"));
	        Thread new2 = new Thread(new DownloadTask("https://downloads-global.3cx.com/downloads/debian10iso/debian-amd64-netinst-3cx.iso","/Users/a839240/Desktop/Upgrade/debian-amd64-netinst-3cx.iso"));
	
	        new1.start();
	        new2.start();
	        new1.setPriority(Thread.MAX_PRIORITY);
	        new2.setPriority(Thread.NORM_PRIORITY);
    }
}
