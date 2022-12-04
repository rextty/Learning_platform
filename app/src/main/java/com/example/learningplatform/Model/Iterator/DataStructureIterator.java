package com.example.learningplatform.Model.Iterator;

import com.example.learningplatform.Model.Composite.DataStructure;

public class DataStructureIterator implements Iterator {

    private DataStructure structure;
    private int index = 0;

    public DataStructureIterator(DataStructure structure) {
        this.structure = structure;
    }

    @Override
    public boolean hasNext() {
        return index < structure.getList().size();
    }

    @Override
    public DataStructure next() {
        DataStructure data = structure.get(index);
        index++;
        return data;
    }
}
