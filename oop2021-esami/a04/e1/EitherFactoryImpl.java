package a04.e1;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class EitherFactoryImpl implements EitherFactory{

    @Override
    public <A, B> Either<A, B> success(B b) {
        return new EitherImpl<>(Optional.empty(), Optional.of(b));
    }

    @Override
    public <A, B> Either<A, B> failure(A a) {
        return new EitherImpl<>(Optional.of(a), Optional.empty());
    }

    @Override
    public <A> Either<Exception, A> of(Supplier<A> computation) {
        try {
             computation.get();
        } catch (Exception e) {
            return new EitherImpl<>(Optional.of(e), Optional.empty());
        }
        return new EitherImpl<>(Optional.empty(), Optional.of(computation.get()));
    }

    @Override
    public <A, B, C> Either<A, List<C>> traverse(List<B> list, Function<B, Either<A, C>> function) {
        for (B b : list) {
            if(function.apply(b).isFailure()) {
                return new EitherImpl<>(Optional.of(function.apply(b).getFailure()).get(), Optional.empty());
            }
        }

        return new EitherImpl<>(Optional.empty(), Optional.of(list.stream().map(function).map(x -> x.getSuccess()).map(x -> x.get()).toList()));
    }
    
    private class EitherImpl<A,B> implements Either<A,B> {

        Optional<A> failure;
        Optional<B> success;

        

        public EitherImpl(Optional<A> failure, Optional<B> success) {
            this.failure = failure;
            this.success = success;
        }

        @Override
        public boolean isFailure() {
            return failure.isPresent();
        }

        @Override
        public boolean isSuccess() {
            return success.isPresent();
        }

        @Override
        public Optional<A> getFailure() {
            return failure;
        }

        @Override
        public Optional<B> getSuccess() {
            return success;
        }

        @Override
        public B orElse(B other) {
            return success.isPresent() ? success.get() : other;
        }

        @Override
        public <B1>Either<A,B1> map(Function<B,B1> function) {
            return success.isPresent() ? new EitherImpl<>(Optional.empty(), Optional.of(function.apply(success.get()))): new EitherImpl<>(failure, Optional.empty());
        }

        @Override
        public Either flatMap(Function function) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public <A1>Either<A1,B> filterOrElse(Predicate<B> predicate, A1 failure) {
            if(this.failure.isPresent()) {
                return new EitherImpl<>(Optional.of(failure), Optional.empty());
            }
            return predicate.test(success.get()) ? new EitherImpl<>(Optional.empty(), success) : new EitherImpl<>(Optional.of(failure), Optional.empty());
        }

        @Override
        public <C> C fold(Function<A,C> funFailure, Function<B,C> funSuccess) {
            return success.isPresent() ? funSuccess.apply(success.get()) : funFailure.apply(failure.get());
        }

    }
}
