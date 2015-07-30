/**
 * 
 */
package com.alan.training.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.joda.time.DateTime;

import com.alan.training.core.AbstractService;
import com.alan.training.model.Token;

/**
 * @author alangabriel
 * 
 */
public class TokenService extends AbstractService {
	/**
	 * Logger.
	 */
	private static final Logger LOG = Logger.getLogger(TokenService.class
	        .getSimpleName());

	public final boolean existe(final Token token) {
		boolean existe = false;
		Token encontrado = null;
		if (token.getId() != null && token.getId().trim().length() > 0) {
			encontrado = this.service.load().type(Token.class)
			        .id(token.getId()).now();
		}
		if (encontrado != null) {
			LOG.info("encontrado");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date(encontrado.getFecha().getMillis()));
			calendar.add(Calendar.HOUR, 2);

			if (new DateTime(calendar.getTimeInMillis()).getMillis() > DateTime
			        .now().getMillis()) {
				existe = true;
				LOG.warning("no hay que renovar");
			}
		}
		return existe;
	}

	public final Token recuperar(String id) {
		Token encontrado = null;
		if (id != null && id.trim().length() > 0) {
			encontrado = this.service.load().type(Token.class).id(id).now();
		}
		return encontrado;
	}

	public final Token guardar(final Token token) {
		LOG.info("guardando");
		token.setFecha(new DateTime());
		this.service.save().entity(token).now();
		return token;
	}

	public final List<Token> listado(final String email) {
		final List<Token> existentes = this.service.load().group(Token.class)
		        .type(Token.class).list();
		final List<Token> parseados = new ArrayList<Token>();
		final long fechaActual = DateTime.now().getMillis();
		for (Token token : existentes) {
			if (email.compareTo(token.getId()) != 0) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(new Date(token.getFecha().getMillis()));
				calendar.add(Calendar.HOUR, 2);
				if (new DateTime(calendar.getTimeInMillis()).getMillis() < fechaActual) {
					token.setEstado(0);
				}
				parseados.add(token);
			}
		}
		return parseados;
	}
}
