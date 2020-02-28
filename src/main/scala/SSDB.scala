import com.twitter.util.Future
import org.nutz.ssdb4j.spi.SSDB

import scala.concurrent.{ExecutionContext, Future}


trait KV[Key, Value] {

  def get(key: Key): Future[Option[Value]]

  def mget(keys: Key*): Future[Option[Map[Key, Value]]]

  def add(k: Key, v: Value): Future[Boolean]

  def madd(k: Key, v: Value): Future[Map[Key, Boolean]]

  def update(k: Key, v: Value): Future[Boolean]

  def mupdate(k: Key, v: Value): Future[Map[Key, Boolean]]

  def remove(key: Key): Future[Boolean]

  def mremove(key: Key): Future[Map[Key, Boolean]]

  def size(): Future[Int]

  def clear(): Future[Boolean]


}

trait SortedSet[Key, Value] {

  def get(key: Key): Future[Option[Value]]

  def mget(keys: Key*): Future[Option[Map[Key, Value]]]

  def add(k: Key, v: Value): Future[Boolean]

  def madd(k: Key, v: Value): Future[Map[Key, Boolean]]

  def update(k: Key, v: Value): Future[Boolean]

  def mupdate(k: Key, v: Value): Future[Map[Key, Boolean]]

  def remove(key: Key): Future[Boolean]

  def mremove(key: Key): Future[Map[Key, Boolean]]

}


case class SsdbKV[Key, Value](client: SSDB)(implicit ec: ExecutionContext = ExecutionContext.global) extends KV[Key, Value] {

  override def get(key: Key): Future[Option[Value]] = {
    Future {
      val resp = client.get(key)
      if (resp.ok()) {
        Some(resp.asInstanceOf[Value])
      }
      None
    }
  }

  override def mget(keys: Key*): Future[Option[Map[Key, Value]]] = {
    Future {
      val resp = client.multi_get(keys)
      if (resp.ok()) Some(resp.asInstanceOf[Value])
      None
    }
  }

  override def add(k: Key, v: Value): Future[Boolean] = {

  }

  override def madd(k: Key, v: Value): Future[Map[Key, Boolean]] = ???

  override def update(k: Key, v: Value): Future[Boolean] = ???

  override def mupdate(k: Key, v: Value): Future[Map[Key, Boolean]] = ???

  override def remove(key: Key): Future[Boolean] = ???

  override def mremove(key: Key): Future[Map[Key, Boolean]] = ???

  override def size(): Future[Int] = ???

  override def clear(): Future[Boolean] = ???
}