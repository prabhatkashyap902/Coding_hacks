static class Print
    {
        private final OutputStream out;
        /*public Print(OutputStream outputStream)
        {
            writer=new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
        }*/
        public Print()
        {
            this.out=System.out;
        }
        public void print(String str)throws IOException
        {
            //buf=str.getBytes();
            for (int i = 0; i < str.length(); i++)
            {
			/*if (i != 0)
			out.write(' ');*/
                out.write(str.charAt(i));
            }
        }
        public void printLine(String str)throws IOException
        {
            print(str);
            out.write('\n');
        }
        public void close()throws IOException
        {
            out.close();
        }
    }
    static class Scan
    {
        private byte[] buf=new byte[1024];
        private int index;
        private InputStream in;
        private int total;
        public Scan()
        {
            in=System.in;
        }
        public int scan()throws IOException
        {
            if(total<0)
                throw new InputMismatchException();
            if(index>=total)
            {
                index=0;
                total=in.read(buf);
                if(total<=0)
                    return -1;
            }
            return buf[index++];
        }
        public int scanInt()throws IOException
        {
            int integer=0;
            int n=scan();
            while(isWhiteSpace(n))
                n=scan();
            int neg=1;
            if(n=='-')
            {
                neg=-1;
                n=scan();
            }
            while(!isWhiteSpace(n))
            {
                if(n>='0'&&n<='9')
                {
                    integer*=10;
                    integer+=n-'0';
                    n=scan();
                }
                else throw new InputMismatchException();
            }
            return neg*integer;
        }
        public double scanDouble()throws IOException
        {
            double doub=0;
            int n=scan();
            while(isWhiteSpace(n))
                n=scan();
            int neg=1;
            if(n=='-')
            {
                neg=-1;
                n=scan();
            }
            while(!isWhiteSpace(n)&&n!='.')
            {
                if(n>='0'&&n<='9')
                {
                    doub*=10;
                    doub+=n-'0';
                    n=scan();
                }
                else throw new InputMismatchException();
            }
            if(n=='.')
            {
                n=scan();
                double temp=1;
                while(!isWhiteSpace(n))
                {
                    if(n>='0'&&n<='9')
                    {
                        temp/=10;
                        doub+=(n-'0')*temp;
                        n=scan();
                    }
                    else throw new InputMismatchException();
                }
            }
            return doub*neg;
        }
        public String scanString()throws IOException
        {
            StringBuilder sb=new StringBuilder();
            int n=scan();
            while(isWhiteSpace(n))
                n=scan();
            while(!isWhiteSpace(n))
            {
                sb.append((char)n);
                n=scan();
            }
            return sb.toString();
        }
        private boolean isWhiteSpace(int n)
        {
            if(n==' '||n=='\n'||n=='\r'||n=='\t'||n==-1)
                return true;
            return false;
        }
    }
    public static void main(String[] args) throws IOException
    {
        Scan scn = new Scan();
        Print print=new Print();
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        int t=scn.scanInt();
        while(t-->0){
            int n=scn.scanInt();
            int[] arr=new int[n];
            for(int i=0;i<n;i++){
                arr[i]=scn.scanInt();
            }
            int q=scn.scanInt();
            while(q-->0){
                int l=scn.scanInt()-1;
                int r=scn.scanInt()-1;
                int point=-1;
                int count=0;
                HashMap<Integer,Integer> map=new HashMap<>();
                for(int i=l;i<=r;i++){
                    if(map.containsKey(arr[i])){
                        point=arr[i];
                        map.put(arr[i],map.get(arr[i])+1);
                    }
                    else map.put(arr[i],1);
                }
                ArrayList<Integer> ar1 =new ArrayList<>(map.values());
                for(int val:ar1){
                    if(val>1){
                        count++;
                    }
                }
                if(count>2){
                    bw.append("0\n");
                    continue;
                }
                else if(count==1){
                    if(map.size()%2==0){
                       bw.append(map.get(point)+"\n");
                    }
                    else bw.append("1\n");
                }
                else {
                    if(map.size()%2==0){
                        bw.append("0\n");
                    }
                    else bw.append(map.size()+"\n");
                }
            }
        }
        bw.close();
    }
