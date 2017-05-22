import java.util.Scanner;

/**
 * Created by 拾光浅唱 on 2017/5/22.
 */
/*
常用Java算法之链表
 */
class DATA2 {
    String key;         //结点关键字
    String name;
    int age;
}

class CLType {           //定义链结构
    DATA2 nodeData = new DATA2();
    CLType nextNode;

    CLType CLAddEnd(CLType head, DATA2 nodeData) {        //追加结点
        CLType node, htemp;
        if ((node = new CLType()) == null) {
            System.out.println("申请内存失败！");
            return null;
        } else {
            node.nodeData = nodeData;       //保存数据
            node.nextNode = null;           //设置结点引用为空，即为表尾
            if (head == null) {
                head = node;
                return head;
            }
            htemp = head;
            while (htemp.nextNode != null) {     //查找链表的尾部
                htemp = htemp.nextNode;
            }
            htemp.nextNode = node;
            return head;
        }
    }

    CLType CLAddFirst(CLType head, DATA2 nodeData) {
        CLType node;
        if ((node = new CLType()) == null) {
            System.out.println("申请内存失败！");//分配内存失败
            return null;
        } else {
            node.nodeData = nodeData;               //保存数据
            node.nextNode = head;                   //指向头引用所指结点
            head = node;                           //头引用指向新增结点
            return head;
        }
    }

    CLType CLFindNode(CLType head, String key) {      //查找结点
        CLType htemp;
        htemp = head;                               //保存链表头部引用
        while (htemp != null) {                      //若结点有效，则进行查找
            if (htemp.nodeData.key.compareTo(key) == 0) {    //若结点关键字与传入关键字一致
                return htemp;                               //返回结点引用
            }
            htemp = htemp.nextNode;                         //处理下一节点
        }
        return null;                                        //返回空引用
    }

    CLType CLInsertNode(CLType head, String findkey, DATA2 nodeData) {     //插入结点
        CLType node, nodetemp;
        if ((node = new CLType()) == null) {                 //分配保存结点的内容
            System.out.println("申请内存失败！");
            return null;
        }
        node.nodeData = nodeData;                           //保存结点中数据
        nodetemp = CLFindNode(head, findkey);
        if (nodetemp != null) {
            node.nextNode = nodetemp.nextNode;
            nodetemp.nextNode = node;
        } else {
            System.out.println("未找到正确的插入位置！");
            node = null;
        }
        return head;
    }

    int CLDeleteNode(CLType head, String key) {
        CLType node, htemp;                                    //node保存删除结点的前一结点
        htemp = head;
        node = head;
        while (htemp != null) {
            if (htemp.nodeData.key.compareTo(key) == 0) {      //找到关键字，执行删除操作
                node.nextNode = htemp.nextNode;               //使前一结点指向当前结点的下一结点
                htemp = null;
                return 1;
            } else {
                node = htemp;
                htemp = htemp.nextNode;
            }
        }
        return 0;
    }

    int CLLength(CLType head) {                          //计算链表长度
        CLType htemp;
        int Len = 0;
        htemp = head;
        while (htemp != null) {                          //遍历整个链表
            Len++;                                      //累加结点数量
            htemp = htemp.nextNode;                     //处理下一结点
        }
        return Len;
    }

    void CLAllNode(CLType head) {                        //遍历列表
        CLType htemp;
        DATA2 nodeData;
        htemp = head;
        System.out.println("当前链表中共有" + CLLength(head) + "个结点，链表所有数据如下：");
        while (htemp != null) {                           //循环处理每一个结点
            nodeData = htemp.nodeData;                   //获取结点数据
            System.out.print("结点（"+nodeData.key + "," + nodeData.name + "," + nodeData.age+"）\n");
            htemp = htemp.nextNode;                     //处理下一结点
        }
    }
}

public class LianBiao {
    public static void main(String[] args) {
        CLType node, head = null;
        CLType CL = new CLType();
        String key, findkey;
        Scanner input = new Scanner(System.in);
        System.out.println("链表测试，先输入链表中的数据，格式：关键字 姓名 年龄");
        do {
            DATA2 nodeData = new DATA2();
            nodeData.key = input.next();
            if (nodeData.key.equals("0")) {
                break;                              //若输入0，则退出
            } else {
                nodeData.name = input.next();
                nodeData.age = input.nextInt();
                head = CL.CLAddEnd(head, nodeData);      //在链表尾部添加结点
            }
        } while (true);
        CL.CLAllNode(head);//显示所有结点

        System.out.println("演示插入结点，输入插入位置的关键字：");
        findkey = input.next();
        System.out.println("输入插入结点的数据（关键字 姓名 年龄）：");
        DATA2 nodeData = new DATA2();
        nodeData.key = input.next();
        nodeData.name = input.next();
        nodeData.age = input.nextInt();
        head = CL.CLInsertNode(head, findkey, nodeData);      //调用插入函数
        CL.CLAllNode(head);//显示所有结点

        System.out.println("演示删除结点，请输入要删除的关键字：");
        key = input.next();
        CL.CLDeleteNode(head, key);
        CL.CLAllNode(head);     //显示所有结点

        System.out.println("演示在链表中查找，请输入查找关键字：");
        key = input.next();
        node = CL.CLFindNode(head, key);
        if (node != null) {      //若返回结点引用有效
            nodeData = node.nodeData;           //获取结点数据
            System.out.println("关键字" + key + "对应的结点为" + nodeData.key + "," + nodeData.name + "," + nodeData.age);
        } else {
            System.out.println("在链表中未找到关键字为" + key + "的结点！");
        }
    }
}
