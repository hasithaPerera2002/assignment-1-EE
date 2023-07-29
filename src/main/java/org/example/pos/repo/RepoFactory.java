package org.example.pos.repo;

import org.example.pos.repo.impl.CustomerRepoImpl;
import org.example.pos.repo.impl.ItemRepoImpl;

public class RepoFactory {

    public enum RepoType {
        ITEM,CUSTOMER,ORDER,ORDER_ITEM
    }
    private static RepoFactory repoFactory;

    public static RepoFactory getInstance(){
      return   repoFactory == null ? repoFactory=new RepoFactory():repoFactory;
    }
    public <T> T getRepo(RepoType repoType){
        switch (repoType){
            case ITEM:
                return (T) new ItemRepoImpl();
            case CUSTOMER:
                return (T) new CustomerRepoImpl();


        }
        return null;
    }
}
