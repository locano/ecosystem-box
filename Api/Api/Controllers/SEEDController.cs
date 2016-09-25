using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Description;
using Api.Models;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using Newtonsoft.Json.Serialization;
using System.IO;
using System.Runtime.Serialization;

namespace Api.Controllers
{
    public class SEEDController : ApiController
    {
        private dbb_GudBoxEntities db = new dbb_GudBoxEntities();

        public IHttpActionResult GetSEED()
        {
            List<object> resultado = new List<object>();
            foreach (var seed in db.SEED.OrderBy(s=>s.name))
            {
                resultado.Add(new { nombre = seed.name, sun = seed.sun, tem_max = seed.temp_max, temp_min = seed.temp_min });
            }
            return Json(resultado);
        }
        //karenliska@gmail.com

        public IHttpActionResult Test()
        {
            var seeds = db.SEED.OrderBy(s => s.name);
            var json = new JObject(new JProperty("seeds"), seeds);
            return Json(json);
        }

        // GET: api/SEED/5
        [ResponseType(typeof(SEED))]
        public IHttpActionResult GetSEED(int id)
        {
            SEED sEED = db.SEED.Find(id);
            if (sEED == null)
            {
                return NotFound();
            }

            return Ok(sEED);
        }

        // PUT: api/SEED/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutSEED(int id, SEED sEED)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != sEED.id_seed)
            {
                return BadRequest();
            }

            db.Entry(sEED).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!SEEDExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return StatusCode(HttpStatusCode.NoContent);
        }

        // POST: api/SEED
        [ResponseType(typeof(SEED))]
        public IHttpActionResult PostSEED(SEED sEED)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.SEED.Add(sEED);

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateException)
            {
                if (SEEDExists(sEED.id_seed))
                {
                    return Conflict();
                }
                else
                {
                    throw;
                }
            }

            return CreatedAtRoute("DefaultApi", new { id = sEED.id_seed }, sEED);
        }

        // DELETE: api/SEED/5
        [ResponseType(typeof(SEED))]
        public IHttpActionResult DeleteSEED(int id)
        {
            SEED sEED = db.SEED.Find(id);
            if (sEED == null)
            {
                return NotFound();
            }

            db.SEED.Remove(sEED);
            db.SaveChanges();

            return Ok(sEED);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool SEEDExists(int id)
        {
            return db.SEED.Count(e => e.id_seed == id) > 0;
        }
    }
}