package co.edu.ucundinamarca.upercth.util;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;
import org.postgresql.geometric.PGpoint;

// https://stackoverflow.com/questions/53548731/how-to-work-with-pgpoint-for-geolocation-using-postgresql
/**
 * @author unknown
 *
 */
public class PGPointType implements UserType {

	/**
	 * 
	 */
	public PGPointType() {
	}

	@Override
	public int[] sqlTypes() {
		return new int[] { Types.VARCHAR };
	}

	@Override
	public Class<PGpoint> returnedClass() {
		return PGpoint.class;
	}

	@Override
	public boolean equals(Object x, Object y) throws HibernateException {
		if (x == y) {
			return true;
		}
		if ((x == null) || (y == null)) {
			return false;
		}
		return x.equals(y);
	}

	@Override
	public int hashCode(Object x) throws HibernateException {
		return x.hashCode();
	}

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner)
			throws HibernateException, SQLException {
		if (names.length == 1) {
			if (rs.wasNull() || rs.getObject(names[0]) == null) {
				return null;
			} else {
				return new PGpoint(rs.getObject(names[0]).toString());
			}
		}
		return null;
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session)
			throws HibernateException, SQLException {
		if (value == null) {
			st.setNull(index, Types.OTHER);
		} else {
			st.setObject(index, value, Types.OTHER);
		}
	}

	@Override
	public Object deepCopy(Object value) throws HibernateException {
		return value;
	}

	@Override
	public boolean isMutable() {
		return Boolean.FALSE;
	}

	@Override
	public Serializable disassemble(Object value) throws HibernateException {
		return (Serializable) value;
	}

	@Override
	public Object assemble(Serializable cached, Object owner) throws HibernateException {
		return cached;
	}

	@Override
	public Object replace(Object original, Object target, Object owner) throws HibernateException {
		return original;
	}

}
