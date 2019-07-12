package org.test.ls;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.Channels;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;

import org.eclipse.lsp4j.jsonrpc.Launcher;
import org.eclipse.lsp4j.jsonrpc.MessageConsumer;
import org.eclipse.lsp4j.services.LanguageClient;

public class ServerSocketLauncher {
	
	public static void main(String[] args) throws Exception {
		
		int serverPort = 5556;

		Function<MessageConsumer, MessageConsumer> wrapper = consumer -> {
			MessageConsumer result = consumer;
			return result;
		};

		System.out.println("Startup Language Server...");

		SimpleLanguageServer server = new SimpleLanguageServer();
		Launcher<LanguageClient> launcher = createSocketLauncher(server, LanguageClient.class, new InetSocketAddress("localhost", serverPort), createServerThreads(), wrapper);
		
		System.out.println("Connect Language Server...");

		server.connect(launcher.getRemoteProxy());
		
		System.out.println("Let Language Server listen...");

		launcher.startListening().get();
	}
	
	/**
	 * Creates the thread pool / executor passed to lsp4j server intialization. From the looks of things,
	 * @return
	 */
	protected static ExecutorService createServerThreads() {
		return Executors.newCachedThreadPool();
	}
	
	private static <T> Launcher<T> createSocketLauncher(
			Object localService, Class<T> remoteInterface,
			SocketAddress socketAddress, ExecutorService executorService,
			Function<MessageConsumer, MessageConsumer> wrapper
	) throws Exception {
		AsynchronousServerSocketChannel serverSocket = AsynchronousServerSocketChannel.open().bind(socketAddress);
		AsynchronousSocketChannel socketChannel = serverSocket.accept().get();
		return Launcher.createIoLauncher(localService, remoteInterface, Channels.newInputStream(socketChannel),
				Channels.newOutputStream(socketChannel), executorService, wrapper);
	}

}
