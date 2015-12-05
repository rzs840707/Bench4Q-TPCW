/**
 * =========================================================================
 * 					Bench4Q version 1.2.1
 * =========================================================================
 * 
 * Bench4Q is available on the Internet at http://forge.ow2.org/projects/jaspte
 * You can find latest version there. 
 * 
 * Distributed according to the GNU Lesser General Public Licence. 
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by   
 * the Free Software Foundation; either version 2.1 of the License, or any
 * later version.
 * 
 * SEE Copyright.txt FOR FULL COPYRIGHT INFORMATION.
 * 
 * This source code is distributed "as is" in the hope that it will be
 * useful.  It comes with no warranty, and no author or distributor
 * accepts any responsibility for the consequences of its use.
 *
 *
 * This version is a based on the implementation of TPC-W from University of Wisconsin. 
 * This version used some source code of The Grinder.
 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
 *  * Initial developer(s): Zhiquan Duan.
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
 * 
 */
/**
 * 2009-9-7
 * author: duanzhiquan
 * Technology Center for Software Engineering
 * Institute of Software, Chinese Academy of Sciences
 * Beijing 100190, China 
 * Email:duanzhiquan07@otcaix.iscas.ac.cn
 * 
 * 
 */
package org.bench4Q.agent.rbe;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Iterator;


import org.bench4Q.agent.rbe.communication.Args;
import org.bench4Q.agent.rbe.communication.TestPhase;

/**
 * @author duanzhiquan
 * 
 */
public class WorkersClosed extends Workers {

	private ArrayList<EB> ebs;
	
	/**
	 * @param startTime
	 * @param triggerTime
	 * @param stdyTime
	 * @param baseLoad
	 * @param randomLoad
	 * @param rate
	 * @param args
	 */
	public WorkersClosed(long startTime, Args args){
		super(startTime, args);
		trace = new ArrayList<ArrayList<Integer>>();
		if (m_args.isReplay()){
			FileInputStream fi;
			try {
				fi = new FileInputStream(m_args.getTime() + "-" );
				ObjectInputStream ois = new ObjectInputStream(fi);
				trace = (ArrayList<ArrayList<Integer>>)ois.readObject();
				ois.close();
				fi.close();
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			} catch (ClassNotFoundException e) {

				e.printStackTrace();
			}

		}
		ebs = new ArrayList<EB>();

		// initialize the pool
		
	}

	void StartEB() {
		int n = 0;
//		long beginTime = System.currentTimeMillis();
		
		int bonus = 0;
		int preload = 0;
		Iterator<TestPhase> it = m_args.getEbs().iterator();
		int num = 0;
		while (it.hasNext()) {
			TestPhase TP = it.next();
			int load = TP.getBaseLoad();
			bonus = load - preload;
			preload = load;

			if (bonus >= 0) {
				for (int j = 0; j < bonus; j++) {
					ArrayList<Integer> tra = new ArrayList<Integer>();
					if (m_args.isReplay()) {
						tra = trace.get(j);
						EB eb = new EBClosed(m_args, tra);
						eb.setDaemon(true);
						// if(j > baseLoad / 2)
						// eb.joke = true;
						eb.start();
						ebs.add(eb);
					} else {
						EB eb = new EBClosed(m_args, tra);
						eb.setDaemon(true);
						eb.start();
						ebs.add(eb);
						trace.add(tra);
					}

				}
			}
			else{
				int extra = - bonus;
				Iterator<EB> itEB = ebs.iterator();
				for(int i = 0; i < extra && itEB.hasNext(); i++){
					((EBClosed)itEB.next()).setTerminate(true);
					itEB.remove();
				}
			}
			
			for (EB eb : ebs) {
				eb.setMix(TP.getMix());
				eb.setThinkTime(TP.getThinktime());
				((EBClosed)eb).setTest(true);
			}

			
			long endTime = System.currentTimeMillis() + TP.getStdyTime() * 1000L;
			
			while (!isStop() && (System.currentTimeMillis() - endTime) < 0) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
			

		
		}
		for (EB eb : ebs) {
			((EBClosed) eb).setTerminate(true);
			// ((EBClosed) eb).stop();
		}
		ebs = null;

	}
	
}
