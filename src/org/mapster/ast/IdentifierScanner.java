package org.mapster.ast;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.sun.source.tree.IdentifierTree;
import com.sun.source.util.TreePathScanner;
import com.sun.source.util.TreeScanner;
import com.sun.source.util.Trees;

public class IdentifierScanner extends TreeScanner<Set<String>, Trees> {

	@Override
	public Set<String> reduce(Set<String> arg0, Set<String> arg1) {
		if (arg0 == null)
			arg0 = new HashSet<>();
		if (arg1 == null)
			arg1 = new HashSet<>();

		arg0.addAll(arg1);
		return arg0;
	}

	@Override
	public Set<String> visitIdentifier(IdentifierTree arg0, Trees arg1) {
		return new HashSet<>(Arrays.asList(arg0.getName().toString()));
	}

}
