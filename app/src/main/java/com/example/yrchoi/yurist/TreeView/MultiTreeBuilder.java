package com.example.yrchoi.yurist.TreeView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class MultiTreeBuilder {


    private TreeNode root;
    private List<TreeNode> tempNodeList;
    private boolean isValidTree = true;

    public MultiTreeBuilder() {
    }

    public MultiTreeBuilder(List<TreeNode> treeNodeList) {
        tempNodeList = treeNodeList;
        generateTree();
    }

    /**
     * generate a tree from the given treeNode or entity list
     */
    private void generateTree() {
        HashMap nodeMap = putNodesIntoMap();
        putChildIntoParent(nodeMap);
    }

    /**
     * put all the treeNodes into a hash table by its id as the key 找出最小id生成根节点
     *
     * @return hashmap that contains the treenodes
     */
    private HashMap putNodesIntoMap() {
        int maxId = Integer.MAX_VALUE;
        HashMap nodeMap = new HashMap<String, TreeNode>();
        Iterator it = tempNodeList.iterator();
        while (it.hasNext()) {
            TreeNode treeNode = (TreeNode) it.next();
            int id = treeNode.getSelfId();
            if (id < maxId) {
                maxId = id;
                this.root = treeNode;
            }
            String keyId = String.valueOf(id);

            nodeMap.put(keyId, treeNode);
            // System.out.println("keyId: " +keyId);
        }
        return nodeMap;
    }

    /**
     * set the parent nodes point to the child nodes
     *
     * @param nodeMap a hashmap that contains all the treenodes by its id as the
     * key
     */
    private void putChildIntoParent(HashMap nodeMap) {
        Iterator it = nodeMap.values().iterator();
        while (it.hasNext()) {
            TreeNode treeNode = (TreeNode) it.next();
            int parentId = treeNode.getParentId();
            String parentKeyId = String.valueOf(parentId);
            if (nodeMap.containsKey(parentKeyId)) {
                TreeNode parentNode = (TreeNode) nodeMap.get(parentKeyId);
                if (parentNode == null) {
                    this.isValidTree = false;
                    return;
                } else {
                    parentNode.addChildNode(treeNode);
                    // System.out.println("childId: " +treeNode.getSelfId()+" parentId: "+parentNode.getSelfId());
                }
            }
        }
    }

    /**
     * initialize the tempNodeList property
     */
    private void initTempNodeList() {
        if (this.tempNodeList == null) {
            this.tempNodeList = new ArrayList<TreeNode>();
        }
    }

    /**
     * add a tree node to the tempNodeList
     *
     * @param treeNode
     */
    public void addTreeNode(TreeNode treeNode) {
        initTempNodeList();
        this.tempNodeList.add(treeNode);
    }

    /**
     * insert a tree node to the tree generated already
     *
     * @return show the insert operation is ok or not
     */
    public boolean insertTreeNode(TreeNode treeNode) {
        boolean insertFlag = root.insertJuniorNode(treeNode);
        return insertFlag;
    }

    /**
     * 从给定树中查找节点
     *
     * @param tree
     * @param id
     * @return
     */
    public static TreeNode getTreeNodeById(TreeNode tree, int id) {
        if (tree == null) {
            return null;
        }
        TreeNode treeNode = tree.findTreeNodeById(id);
        return treeNode;
    }

    /**
     * 查找本数的节点
     *
     * @param id
     * @return
     */
    public TreeNode getTreeNodeById(int id) {
        TreeNode treeNode = this.getRoot().findTreeNodeById(id);
        return treeNode;
    }

    /**
     * 生成树，返回根节点
     *
     * @return
     */
    public TreeNode toTree() {
        return this.getRoot();
    }
//    /**
//     * adapt the entities to the corresponding treeNode
//     *
//     * @param entityList
//     *            list that contains the entities
//     *@return the list containg the corresponding treeNodes of the entities
//     */
//    public static List<TreeNode> changeEnititiesToTreeNodes(List entityList) {
//        OrganizationEntity orgEntity = new OrganizationEntity();
//        List<TreeNode> tempNodeList = new ArrayList<TreeNode>();
//        TreeNode treeNode;
//
//        Iterator it = entityList.iterator();
//        while (it.hasNext()) {
//            orgEntity = (OrganizationEntity) it.next();
//            treeNode = new TreeNode();
//            treeNode.setObj(orgEntity);
//            treeNode.setParentId(orgEntity.getParentId());
//            treeNode.setSelfId(orgEntity.getOrgId());
//            treeNode.setNodeName(orgEntity.getOrgName());
//            tempNodeList.add(treeNode);
//        }
//        return tempNodeList;
//    }

    public boolean isValidTree() {
        return this.isValidTree;
    }

    public TreeNode getRoot() {
        return root;
    }

    private void setRoot(TreeNode root) {
        this.root = root;
    }

    public List<TreeNode> getTempNodeList() {
        return tempNodeList;
    }

    private void setTempNodeList(List<TreeNode> tempNodeList) {
        this.tempNodeList = tempNodeList;
    }

}
