/*
 * Copyright (C) 2017 normal
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package loggerconfigurator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.NOPLogger;

/**
 *
 *
 * @author dosdiaopfhj
 */
public final class LoggerConfigurator {



	private static final Logger LOG = LoggerFactory.getLogger(LoggerConfigurator.class);

	/** シングルトンパターンのためのインスタンス. */
	private static final LoggerConfigurator INSTANCE = new LoggerConfigurator();

	private LoggerConfigurator() {
	}

	/**
	 * デバッグ用メンバ。
	 */
	private String _lastClassName = null;

	/**
	 * このクラスのインスタンスを取得
	 *
	 * @return このクラスのインスタンス
	 */
	public static LoggerConfigurator getlnstance() {
		return INSTANCE;
	}

	private final class LU_I extends SecurityManager
	{
	    public Class<?> getClassObj()
	    {
	        return getClassContext()[2];
	    }
	}


	/**
	 * ロガーにクラス名を自動設定する。
	 *
	 * @return クラスにログの出力を抑止するアノテーションがある場合、NOPLoggerを返す。クラスにログの出力を抑止するアノテーションが無い場合、このメソッドを呼び出したクラスの名前をセットしたロガーを返す。クラスの名前が見つからなかった場合、nullを返す。
	 */
	public synchronized Logger getCallerLogger() {
		final String className;
		final Class<?> target_class;

			final LU_I sman=new LU_I();
			target_class = sman.getClassObj();
			className = target_class.getSimpleName();

		final SuppressLog issup = target_class.getAnnotation(SuppressLog.class);
		if ((issup != null) && issup.value()) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("このクラスはログ出力抑止中です。NOPLoggerを返却します。クラス = {}", className);
			}
			return NOPLogger.NOP_LOGGER;
		}

		if (LOG.isDebugEnabled()) {
			this._lastClassName = className;
		} else {
			this._lastClassName = null;
		}

		if (LOG.isDebugEnabled()) {
			LOG.debug("クラス名をセットしたロガーを返却します。クラス = {}", className);
		}
		return LoggerFactory.getLogger(className);

	}

	/**
	 * デバッグ用。
	 * @return デフォルト値としてnullを返す。ログレベルがデバッグ以下の時、直前にロガーにセットしたクラスがあればその名前を返す。ログレベルがデバッグ以下ではない場合はnullを返す。
	 */
	protected synchronized String getLastClassName() {
		return _lastClassName;
	}
}
