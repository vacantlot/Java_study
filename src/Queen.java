public class Queen{
	private final int size;       //���̴�С��Ҳ��ʾ�ʺ��
	private int[] location;		  //�ʺ������̵�ÿ���ϵ��е�λ��
	private int[] colsOccupied;   //�ʺ���������ռ�ݵ���
	private int[] cross1Occupied; //�ʺ���������ռ�ݵ����Խ���
	private int[] cross2Occupied; //�ʺ���������ռ�ݵķ��Խ���
	private static int count;     //��������ĸ���
	
	private static final int STATUS_OCCUPIED = 1; //ռ��״̬
	private static final int STATUS_OCCUPY_CANCELED = 0; //δռ��״̬
	
	public Queen(int size) {
		//��ʼ��
		this.size = size;
		location = new int[size];
		colsOccupied = new int[size];
		cross1Occupied = new int[2*size];
		cross2Occupied = new int[2*size];
	}
	public void printLocation() {
		System.out.println("�����ǻʺ��������ϵĵ�"+count+"�ְڷ�λ��");
		for (int i = 0; i < size; i++) {
			System.out.println("�У�"+i+" ��:"+location[i]);
		}
	}
	/**�ж�λ�ã�i,j���Ƿ�Ϊռ��״̬*/
	private boolean isOccupied(int i, int j) {
		return(colsOccupied[j] == STATUS_OCCUPIED) || (cross1Occupied[i-j+size-1] == STATUS_OCCUPIED) || (cross2Occupied[i+j] == STATUS_OCCUPIED);
	}
	/**
	 * �������flag��1����ʾռ��λ�ã�i��j��
	 * �������flag��0����ʾȡ��ռ��λ�ã�i��j�� */
	private void setStatus(int i, int j ,int flag) {
		colsOccupied[j] = flag;
		cross1Occupied[i-j+size-1] = flag;
		cross2Occupied[i+j] = flag;
	}
	/**�ӵ�i�п�ʼ�ڷŻʺ�*/
	public void place(int i) {
		for (int j = 0; j < size; j++) {  //�ڵ�i�У��ֱ��԰ѻʺ����ÿһ����
			if (!isOccupied(i, j)) {      //�ж��Ƿ�ռ��
				location[i] = j;          //�ڷŻʺ��ڵ�i�аѻʺ���ڵ�j��
				setStatus(i, j, STATUS_OCCUPIED); //����ռ�죨i,j��λ��
				if (i<size-1) {
					place(i+1);   //������лʺ�û�а��꣬�ݹ�ڷ���һ�еĻʺ�
				}
				else {
					count++;      //ͳ���ܽ�������ĸ���
					printLocation();//��ӡ��λʺ��λ��
				}
				//���ݣ�����ռ��λ�ã�i,j��
				setStatus(i, j, STATUS_OCCUPY_CANCELED);
			}
			
		}
	}
	
	public void start() {
		place(0);  //��0�п�ʼ
	}
}