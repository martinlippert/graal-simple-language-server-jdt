package org.test.ls;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.eclipse.lsp4j.CompletionItem;
import org.eclipse.lsp4j.CompletionItemKind;
import org.eclipse.lsp4j.CompletionList;
import org.eclipse.lsp4j.CompletionParams;
import org.eclipse.lsp4j.DidChangeTextDocumentParams;
import org.eclipse.lsp4j.DidCloseTextDocumentParams;
import org.eclipse.lsp4j.DidOpenTextDocumentParams;
import org.eclipse.lsp4j.DidSaveTextDocumentParams;
import org.eclipse.lsp4j.TextDocumentIdentifier;
import org.eclipse.lsp4j.TextDocumentItem;
import org.eclipse.lsp4j.jsonrpc.messages.Either;
import org.eclipse.lsp4j.services.TextDocumentService;
import org.test.ls.jdt.JDTParser;

public class SimpleTextDocumentService implements TextDocumentService {

	private String doc;

	public void didOpen(DidOpenTextDocumentParams params) {
		doc = params.getTextDocument().getText();
	}

	public void didChange(DidChangeTextDocumentParams params) {
	}

	public void didClose(DidCloseTextDocumentParams params) {
	}

	public void didSave(DidSaveTextDocumentParams params) {
		doc = params.getText();
	}
	
	public CompletableFuture<Either<List<CompletionItem>, CompletionList>> completion(CompletionParams position) {
        CompletionItem item1 = new CompletionItem();
        item1.setLabel("Completion 1");
        item1.setKind(CompletionItemKind.Text);
        item1.setDetail("Details 1");

        CompletionItem item2 = new CompletionItem();
        item2.setLabel("Completion 2");
        item2.setKind(CompletionItemKind.Text);
        item2.setDetail("Details 2");

        List<CompletionItem> completions = new ArrayList<>();
        completions.add(item1);
        completions.add(item2);
        
        try {
        	JDTParser.parseDoc(doc);
        }
        catch (Exception e) {
        	e.printStackTrace();
        }

        return CompletableFuture.completedFuture(Either.forRight(new CompletionList(false, completions)));
	}
	
	@Override
	public CompletableFuture<CompletionItem> resolveCompletionItem(CompletionItem unresolved) {
        return CompletableFuture.completedFuture(null);
	}

}
