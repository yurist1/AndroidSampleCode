package com.example.gbkim.gubonny.TreeView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.gbkim.gubonny.R;

import java.util.ArrayList;
import java.util.List;

public class TreeViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_view);

        List list = new ArrayList();
        list.add(new TreeNode(1, 0, "1"));
        list.add(new TreeNode(11, 1, "1.1"));
        list.add(new TreeNode(12, 1, "1.2"));
        list.add(new TreeNode(111, 11, "1.1.1"));
        list.add(new TreeNode(112, 11, "1.1.2"));
        list.add(new TreeNode(121, 12, "1.2.1"));
        list.add(new TreeNode(122, 12, "1.2.2"));

        MultiTreeBuilder tree = new MultiTreeBuilder(list);
        System.out.println( tree.isValidTree());
        System.out.println( tree.getTreeNodeById(121).getNodeName());
        tree.toTree().levelOrderTraversal();
    }
}
