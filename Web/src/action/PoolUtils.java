package action;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PoolUtils {
	private static ExecutorService executorEnerge = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*2+1);
	
	
}
