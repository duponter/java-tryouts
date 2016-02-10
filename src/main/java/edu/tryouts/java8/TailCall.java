package edu.tryouts.java8;

import java.util.function.Supplier;

public abstract class TailCall<T> {
	public abstract TailCall<T> resume();

	public abstract T eval();

	public abstract boolean isSuspend();

	private TailCall() {
	}

	public static class Return<T> extends TailCall<T> {
		private final T t;

		private Return(T t) {
			this.t = t;
		}

		@Override
		public T eval() {
			return t;
		}

		@Override
		public boolean isSuspend() {
			return false;
		}

		@Override
		public TailCall<T> resume() {
			throw new IllegalStateException("Return has no resume");
		}
	}

	public static class Suspend<T> extends TailCall<T> {
		private final Supplier<TailCall<T>> resume;

		private Suspend(Supplier<TailCall<T>> resume) {
			this.resume = resume;
		}

		@Override
		public T eval() {
			TailCall<T> tailRec = this;
			while (tailRec.isSuspend()) {
				tailRec = tailRec.resume();
			}
			return tailRec.eval();
		}

		@Override
		public boolean isSuspend() {
			return true;
		}

		@Override
		public TailCall<T> resume() {
			return resume.get();
		}
	}

	public static <T> Return<T> ret(T t) {
		return new Return<>(t);
	}

	public static <T> Suspend<T> sus(Supplier<TailCall<T>> s) {
		return new Suspend<>(s);
	}


	/**
	 * Command line application execution.
	 *
	 * @param args Array of Strings referencing the command line arguments.
	 */
	public static void main(String[] args) {
		System.out.println("args = " + addRec(1000000000, 3).eval());
	}

	static TailCall<Integer> addRec(int x, int y) {
		return y == 0
				? TailCall.ret(x)
				: TailCall.sus(() -> addRec(x +1, y-1));
	}
}
