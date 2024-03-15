export class POIDensityController {
  constructor(DensityService) {
    this.DensityService = DensityService;
  }

  byType(req, res) {
    this.DensityService.byType(req.query.attractionType).then((r) => {
      if (r) res.json(r);
      else
        res
          .status(418)
          .send({
            content:
              "Something went wrong here that shouldn't have. Thus I am a Teapot now",
          })
          .end();
    });
  }
}
