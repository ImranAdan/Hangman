package org.adani.hangman.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Logging aspect for controller classes.
 */
@Aspect
@Component
public class ControllerLogAspect {

	private static final Logger LOGGER = LoggerFactory.getLogger(ControllerLogAspect.class);

	@Before("execution(* org.adani.hangman..controller..*(..))")
	protected void onEntry(JoinPoint jp) {
		String args = jointPointArgs(jp);
		String actionMessage = "ENTERING: {" + jp.getSignature().getDeclaringTypeName() + "::"
				+ jp.getSignature().getName() + "( " + args + " )" + "}";
		LOGGER.info(actionMessage);
	}

	@After("execution(* org.adani.hangman..controller..**(..))")
	protected void onExit(JoinPoint jp) {
		String args = jointPointArgs(jp);
		String actionMessage = "EXITING: {" + jp.getSignature().getDeclaringTypeName() + "::"
				+ jp.getSignature().getName() + "( " + args + " )" + "}";
		LOGGER.info(actionMessage);
	}

	@AfterThrowing(pointcut = ("execution(* org.adani.hangman..controller..**(..))"), throwing = "ex")
	protected void onExceptionThrown(JoinPoint jp, Throwable ex) {
		String fault = "EXCEPTION [ " + ex.getMessage() + " ]";
		LOGGER.info(fault);
		String args = jointPointArgs(jp);
		String actionMessage = fault + "EXIT: [" + jp.getSignature().getDeclaringTypeName() + "::"
				+ jp.getSignature().getName() + "( " + args + " )]";
		LOGGER.info(actionMessage);
	}

	private static String jointPointArgs(JoinPoint jp) {
		String args = "[";

		if (jp.getArgs().length > 0) {
			for (Object o : jp.getArgs()) {
				if (o != null)
					args += o.toString() + ", ";
			}
		}

		args += (args.length() > 1) ? args.substring(0, args.length() - 2) + "]]" : "]";
		return args;
	}
}
