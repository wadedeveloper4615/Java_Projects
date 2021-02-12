
package org.apache.bcel.verifier;

public abstract class NativeVerifier {

    private NativeVerifier() {
    }

    public static void main(final String[] args) {
        if (args.length != 1) {
            System.out.println("Verifier front-end: need exactly one argument.");
            System.exit(1);
        }
        final int dotclasspos = args[0].lastIndexOf(".class");
        if (dotclasspos != -1) {
            args[0] = args[0].substring(0, dotclasspos);
        }
        args[0] = args[0].replace('/', '.');
        // System.out.println(args[0]);
        try {
            Class.forName(args[0]);
        } catch (final ExceptionInInitializerError eiie) { // subclass of LinkageError!
            System.out.println("NativeVerifier: ExceptionInInitializerError encountered on '" + args[0] + "'.");
            System.out.println(eiie);
            System.exit(1);
        } catch (final LinkageError le) {
            System.out.println("NativeVerifier: LinkageError encountered on '" + args[0] + "'.");
            System.out.println(le);
            System.exit(1);
        } catch (final ClassNotFoundException cnfe) {
            System.out.println("NativeVerifier: FILE NOT FOUND: '" + args[0] + "'.");
            System.exit(1);
        } catch (final Throwable t) { // OK to catch Throwable here as we call exit.
            System.out.println("NativeVerifier: Unspecified verification error on '" + args[0] + "'.");
            System.exit(1);
        }
        System.out.println("NativeVerifier: Class file '" + args[0] + "' seems to be okay.");
        System.exit(0);
    }
}
