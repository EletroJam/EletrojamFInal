package aspects;

public aspect Test {
	
	static int z;
	
	/*
	int around(): execution (public static int getComp()){
		z = proceed();
		return z;
	}
	
	void around(): execution (private void handleOk()){
		
		
		
	}
	
	*/

	
	/*
	pointcut handle() : execution (private void handleOk() );
	
	after (): handle(){
		ControleTelaClienteEd cd =  ControleTelaClienteEd.getInstance() ;
		cd.refreshPersonTable(z);
		//System.out.println("hell");
	}*/

}
