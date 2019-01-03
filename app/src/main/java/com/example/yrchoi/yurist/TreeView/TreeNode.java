package com.example.yrchoi.yurist.TreeView;


import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.ArrayDeque;

public class TreeNode implements Serializable {
    private int parentId; //父节点id
    private int selfId; //子节点id
    protected String nodeName; //节点名称（默认"TreeNode"）
    protected Object obj; //节点所含内容
    protected TreeNode parentNode; //指向父节点
    protected List<TreeNode> childList; //指向子节点（多个子节点）

    /**
     * 生成一个原始TreeNode（无初始化）
     */
    public TreeNode() {
        initChildList();
    }

    /**
     * 生成一个带有自身id和父节点id的TreeNode
     *
     * @param selfId   自身id
     * @param parentId 父节点id
     */
    public TreeNode(int selfId, int parentId) {
        this.parentId = parentId;
        this.selfId = selfId;
        this.nodeName = "TreeNode";
        this.obj = null;
        initChildList();
    }

    /**
     * 生成一个带有自身id，父节点id，节点名的TreeNode
     *
     * @param selfId   自身id
     * @param parentId 父节点id
     * @param nodeName 节点名称
     */
    public TreeNode(int selfId, int parentId, String nodeName) {
        this.parentId = parentId;
        this.selfId = selfId;
        this.nodeName = nodeName;
        this.obj = null;
        initChildList();
    }

    /**
     * 生成一个带有自身id，父节点id，节点名，节点附加内容的TreeNode
     *
     * @param selfId   自身id
     * @param parentId 父节点id
     * @param nodeName 节点名称
     * @param obj      节点附件内容
     */
    public TreeNode(int parentId, int selfId, String nodeName, Object obj) {
        this.parentId = parentId;
        this.selfId = selfId;
        this.nodeName = nodeName;
        this.obj = obj;
        initChildList();
    }

    /**
     * 是否为叶节点
     *
     * @return 是，返回true；否，返回false。
     */
    public boolean isLeaf() {
        if (childList == null) {
            return true;
        } else {
            if (childList.isEmpty()) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * 插入一个child节点到当前节点中
     *
     * @param treeNode 树节点
     */
    public void addChildNode(TreeNode treeNode) {
        initChildList();
        childList.add(treeNode);
    }

    private void initChildList() {
        if (childList == null)
            childList = new ArrayList<TreeNode>();
    }

//    /**
//     * 检查生成的树是否为有效树
//     * @return
//     */
//    public boolean isValidTree() {
//        return true;
//    }

    /**
     * 返回当前节点的父辈节点集合
     *
     * @return
     */
    public List<TreeNode> getElders() {
        List<TreeNode> elderList = new ArrayList<TreeNode>();
        TreeNode parentNode = this.getParentNode();
        if (parentNode == null) {
            return elderList;
        } else {
            elderList.add(parentNode);
            elderList.addAll(parentNode.getElders());
            return elderList;
        }
    }

    /**
     * 返回当前节点的晚辈集合
     *
     * @return
     */
    public List<TreeNode> getJuniors() {
        List<TreeNode> juniorList = new ArrayList<TreeNode>();
        List<TreeNode> childList = this.getChildList();
        if (childList == null) {
            return juniorList;
        } else {
            int childNumber = childList.size();
            for (int i = 0; i < childNumber; i++) {
                TreeNode junior = childList.get(i);
                juniorList.add(junior);
                juniorList.addAll(junior.getJuniors());
            }
            return juniorList;
        }
    }

    /**
     * 返回当前节点的孩子集合
     *
     * @return
     */
    public List<TreeNode> getChildList() {
        return childList;
    }

    /**
     * 删除节点和它下面的晚辈
     */
    public void deleteNode() {
        TreeNode parentNode = this.getParentNode();
        int id = this.getSelfId();

        if (parentNode != null) {
            parentNode.deleteChildNode(id);
        }
    }

    /**
     * 删除当前节点的某个子节点
     *
     * @param childId
     */
    public void deleteChildNode(int childId) {
        List<TreeNode> childList = this.getChildList();
        int childNumber = childList.size();
        for (int i = 0; i < childNumber; i++) {
            TreeNode child = childList.get(i);
            if (child.getSelfId() == childId) {
                childList.remove(i);
                return;
            }
        }
    }

    /**
     * 动态的插入一个新的节点到当前树中
     *
     * @param treeNode 新节点
     * @return 插入是否成功
     */
    public boolean insertJuniorNode(TreeNode treeNode) {
        int juniorParentId = treeNode.getParentId();
        if (this.parentId == juniorParentId) {
            addChildNode(treeNode);
            return true;
        } else {
            List<TreeNode> childList = this.getChildList();
            int childNumber = childList.size();
            boolean insertFlag;

            for (int i = 0; i < childNumber; i++) {
                TreeNode childNode = childList.get(i);
                insertFlag = childNode.insertJuniorNode(treeNode);
                if (insertFlag == true)
                    return true;
            }
            return false;
        }
    }

    /**
     * 找到树中某个节点
     *
     * @param id
     * @return
     */
    public TreeNode findTreeNodeById(int id) {
        if (this.selfId == id)
            return this;
        if (childList.isEmpty() || childList == null) {
            return null;
        } else {
            int childNumber = childList.size();
            for (int i = 0; i < childNumber; i++) {
                TreeNode child = childList.get(i);
                TreeNode resultNode = child.findTreeNodeById(id);
                if (resultNode != null) {
                    return resultNode;
                }
            }
            return null;
        }
    }

    /**
     * 遍历一棵树，层次遍历,换行
     */
    public void traverseDeepFirst() {
        if (selfId < 0)
            return;
        println(this.selfId);
        if (childList == null || childList.isEmpty())
            return;
        int childNumber = childList.size();
        for (int i = 0; i < childNumber; i++) {
            TreeNode child = childList.get(i);
            child.traverseDeepFirst();
        }
    }
//
//     /**
//     * 深度优先遍历，相当于先根遍历
//     * 采用非递归实现
//     * 需要辅助数据结构：栈
//     */
//    public void depthOrderTraversal(){
//        if(root==null){
//            System.out.println("empty tree");
//            return;
//        }
//        ArrayDeque<TreeNode> stack=new ArrayDeque<TreeNode>();
//        stack.push(root);
//        while(stack.isEmpty()==false){
//            TreeNode node=stack.pop();
//            System.out.print(node.value+"    ");
//            if(node.right!=null){
//                stack.push(node.right);
//            }
//            if(node.left!=null){
//                stack.push(node.left);
//            }
//        }
//        System.out.print("\n");
//    }
//
//
//    /**
//     *                  13
//     *                 /  \
//     *               65    5
//     *              /  \    \
//     *             97  25   37
//     *            /    /\   /
//     *           22   4 28 32
//     */


    /**
     * 广度优先遍历
     * 采用非递归实现
     * 需要辅助数据结构：队列
     */
    public void levelOrderTraversal() {
        if (selfId < 0)
            return;
        if (childList == null || childList.isEmpty())
            return;
        ArrayDeque<TreeNode> queue = new ArrayDeque<TreeNode>();
        queue.add(this);
        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            System.out.print(node.getSelfId() + "    ");
            if (!node.isLeaf()) {
                List childList = node.getChildList();
                for (Object object : childList) {
                    queue.add((TreeNode) object);
                }
            }
        }
        System.out.print("\n");
    }
//
//    /**
//     * 遍历一棵树，层次遍历，打印成树形式
//     */
//    public void traverseBroadFirst() {
//        if (selfId < 0)
//            return;
//        print(this.selfId+" ");
//        if (childList == null || childList.isEmpty())
//            return;
//        //如果没有后续兄弟节点则换行
//        if()
//            println("");
//        int childNumber = childList.size();
//        for (int i = 0; i < childNumber; i++) {
//            TreeNode child = childList.get(i);
//            child.traverseBroadFirst();
//        }
//    }

    public void println(String content) {
        System.out.println(content);
    }

    public void println(int content) {
        System.out.println(String.valueOf(content));
    }

    public void print(String content) {
        System.out.print(content);
    }

    public void print(int content) {
        System.out.print(String.valueOf(content));
    }

    public void setChildList(List<TreeNode> childList) {
        this.childList = childList;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getSelfId() {
        return selfId;
    }

    public void setSelfId(int selfId) {
        this.selfId = selfId;
    }

    public TreeNode getParentNode() {
        return parentNode;
    }

    public void setParentNode(TreeNode parentNode) {
        this.parentNode = parentNode;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
