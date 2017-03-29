public class Queen{
	private final int size;       //棋盘大小，也表示皇后的
	private int[] location;		  //皇后在棋盘的每列上的列的位置
	private int[] colsOccupied;   //皇后在棋盘上占据的列
	private int[] cross1Occupied; //皇后在棋盘上占据的正对角线
	private int[] cross2Occupied; //皇后在棋盘上占据的反对角线
	private static int count;     //解决方案的个数
	
	private static final int STATUS_OCCUPIED = 1; //占领状态
	private static final int STATUS_OCCUPY_CANCELED = 0; //未占领状态
	
	public Queen(int size) {
		//初始化
		this.size = size;
		location = new int[size];
		colsOccupied = new int[size];
		cross1Occupied = new int[2*size];
		cross2Occupied = new int[2*size];
	}
	public void printLocation() {
		System.out.println("以下是皇后在棋盘上的第"+count+"种摆放位置");
		for (int i = 0; i < size; i++) {
			System.out.println("行："+i+" 列:"+location[i]);
		}
	}
	/**判断位置（i,j）是否为占领状态*/
	private boolean isOccupied(int i, int j) {
		return(colsOccupied[j] == STATUS_OCCUPIED) || (cross1Occupied[i-j+size-1] == STATUS_OCCUPIED) || (cross2Occupied[i+j] == STATUS_OCCUPIED);
	}
	/**
	 * 如果参数flag是1，表示占领位置（i，j）
	 * 如果参数flag是0，表示取消占领位置（i，j） */
	private void setStatus(int i, int j ,int flag) {
		colsOccupied[j] = flag;
		cross1Occupied[i-j+size-1] = flag;
		cross2Occupied[i+j] = flag;
	}
	/**从第i行开始摆放皇后*/
	public void place(int i) {
		for (int j = 0; j < size; j++) {  //在第i行，分别尝试把皇后放在每一列上
			if (!isOccupied(i, j)) {      //判断是否被占据
				location[i] = j;          //摆放皇后，在第i行把皇后放在第j列
				setStatus(i, j, STATUS_OCCUPIED); //宣布占领（i,j）位置
				if (i<size-1) {
					place(i+1);   //如果还有皇后没有摆完，递归摆放下一行的皇后
				}
				else {
					count++;      //统计总解决方案的个数
					printLocation();//打印这次皇后的位置
				}
				//回溯，撤销占领位置（i,j）
				setStatus(i, j, STATUS_OCCUPY_CANCELED);
			}
			
		}
	}
	
	public void start() {
		place(0);  //从0行开始
	}
}