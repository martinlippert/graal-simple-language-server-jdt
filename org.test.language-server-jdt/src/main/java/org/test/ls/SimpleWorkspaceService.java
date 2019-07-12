package org.test.ls;

import org.eclipse.lsp4j.DidChangeConfigurationParams;
import org.eclipse.lsp4j.DidChangeWatchedFilesParams;
import org.eclipse.lsp4j.services.WorkspaceService;

public class SimpleWorkspaceService implements WorkspaceService {

	public void didChangeConfiguration(DidChangeConfigurationParams params) {
	}

	public void didChangeWatchedFiles(DidChangeWatchedFilesParams params) {
	}

}
