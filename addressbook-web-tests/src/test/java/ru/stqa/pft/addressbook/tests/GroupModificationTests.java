package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

/**
 * Created by Алёна on 24.05.2017.
 */
public class GroupModificationTests extends TestBase {
  @Test
  public void testGroupModification(){

    app.getNavigationHelper().gotoGroupPage();

    if (! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size()-1);// редактирование  последнего элемента
    app.getGroupHelper().initGroupModification();

    GroupData group = new GroupData(before.get(before.size()-1).getId(), "group1", "test2", "test3");
    app.getGroupHelper().fillGroupForm(group);
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returntoGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size()-1);
    before.add(group);
    Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));


  }
}
