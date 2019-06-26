package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Branch;
import com.acme.banking.dbo.service.Reporting;
import org.junit.Assert;
import org.junit.Test;

import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ReportingTest {

    @Test
    public void shouldReportingPrintAccountWhenNotHaveChildren() {

        List dummyAccounts = new ArrayList<>();
        Branch branchWithoutChildren = new Branch(dummyAccounts);

        Reporting sut = new Reporting();
        String reportStr = sut.getReport(branchWithoutChildren);

        Assert.assertTrue(reportStr.contains("accounts:"));
    }


    @Test
    public void shouldReportingPrintBranchesIfHaveChildren() {
        Reporting sut = new Reporting();
        Collection<Account> dummyAccounts = new ArrayList<>();
        Branch branchWithChildren = new Branch(dummyAccounts);
        Branch childrenBranch = new Branch(dummyAccounts);
        branchWithChildren.addChildren(childrenBranch);

        String reportStr = sut.getReport(branchWithChildren);

        Assert.assertTrue(reportStr.contains("branches:"));
    }
}
