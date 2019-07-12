package org.test.ls;

import java.util.concurrent.CompletableFuture;

import org.eclipse.lsp4j.CompletionOptions;
import org.eclipse.lsp4j.InitializeParams;
import org.eclipse.lsp4j.InitializeResult;
import org.eclipse.lsp4j.ServerCapabilities;
import org.eclipse.lsp4j.TextDocumentSyncKind;
import org.eclipse.lsp4j.services.LanguageClient;
import org.eclipse.lsp4j.services.LanguageClientAware;
import org.eclipse.lsp4j.services.LanguageServer;
import org.eclipse.lsp4j.services.TextDocumentService;
import org.eclipse.lsp4j.services.WorkspaceService;

public class SimpleLanguageServer implements LanguageServer, LanguageClientAware {

	private final SimpleTextDocumentService textDocumentService;
	private final SimpleWorkspaceService workspaceService;

	public SimpleLanguageServer() {
		this.textDocumentService = new SimpleTextDocumentService();
		this.workspaceService = new SimpleWorkspaceService();
	}

	public CompletableFuture<InitializeResult> initialize(InitializeParams params) {
        ServerCapabilities capabilities = new ServerCapabilities();
        capabilities.setTextDocumentSync(TextDocumentSyncKind.Full);
        capabilities.setCodeActionProvider(false);
        capabilities.setColorProvider(false);
        capabilities.setCompletionProvider(new CompletionOptions(true, null));

        return CompletableFuture.completedFuture(new InitializeResult(capabilities));
	}

	public CompletableFuture<Object> shutdown() {
        return CompletableFuture.completedFuture(null);
	}

	public void exit() {
		System.exit(0);
	}

	public TextDocumentService getTextDocumentService() {
		return this.textDocumentService;
	}

	public WorkspaceService getWorkspaceService() {
		return this.workspaceService;
	}

	@Override
	public void connect(LanguageClient client) {
	}

}
