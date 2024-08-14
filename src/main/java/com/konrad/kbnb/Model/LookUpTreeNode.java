package com.konrad.kbnb.Model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;

public class LookUpTreeNode {
    private Map<String, LookUpTreeNode> children;
    private LookUpMatch match;
}
